package com.timdiadiem.controller;

import com.timdiadiem.model.Blog;
import com.timdiadiem.service.impl.BlogAddRequest;
import com.timdiadiem.service.pkInterface.BlogCategoryService;
import com.timdiadiem.service.pkInterface.BlogService;
import com.timdiadiem.service.pkInterface.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping
    public ModelAndView showBlogsPage(@PageableDefault(size=6) Pageable pageable){
        return new ModelAndView("/views-web/blog","listBlogs",blogService.findAll(pageable));
    }

    @GetMapping("/search")
    public ModelAndView showListBlogsByTag(@RequestParam("tagId") Long tagId){
        return new ModelAndView("/views-web/blog","listBlogs",blogService.findByTag(tagId));
    }

    @GetMapping("/add")
    public ModelAndView showAddNewBlogForm(){
        return new ModelAndView("views-web/blog-post","blogCategories",blogCategoryService.findAll());
    }

    @PostMapping("/add")
    public void postBlog(@RequestBody BlogAddRequest blogAddRequest){
        blogService.saveBlog(blogAddRequest);
    }

    @GetMapping("/{blogId}")
    public ModelAndView showBlog(@PathVariable Long blogId){
        if (blogService.findById(blogId) == null){
            return new ModelAndView("/views-error/blog-not-found");
        }
        ModelAndView modelAndView = new ModelAndView("/views-web/blog-single");
        modelAndView.addObject("blog",blogService.findById(blogId));
        modelAndView.addObject("popularTags",blogTagService.find10PopularTags());
        modelAndView.addObject("blogCategoriesMap",blogCategoryService.findAllCategoriesAndCountNumberBlogHasIt());
        List<Blog> a = blogService.findAllRecentBlogs();
        modelAndView.addObject("recentBlogs",blogService.findAllRecentBlogs());
        return modelAndView;
    }


}
