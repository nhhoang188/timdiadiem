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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/searchbytag")
    public ModelAndView showListBlogsByTag(@RequestParam("tagid") Long tagId){
        return new ModelAndView("/views-web/blog","listBlogs",blogService.findByTag(tagId));
    }
    @GetMapping("/searchbydaycreate")
    public ModelAndView showListBlogsByCreatedAt(@RequestParam("createdat") String createdAt){
        return new ModelAndView("/views-web/blog","listBlogs",blogService.findByCreatedAt(LocalDate.parse(createdAt)));
    }
    @GetMapping("/searchbycategory")
    public ModelAndView showListBlogsByCategory(@RequestParam("categoryid") Long categoryId){
        List<Blog> blogs = blogService.findByCategory(categoryId);
        return new ModelAndView("/views-web/blog","listBlogs",blogService.findByCategory(categoryId));
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
        blogService.view(blogId);
        ModelAndView modelAndView = new ModelAndView("/views-web/blog-single");
        modelAndView.addObject("blog",blogService.findById(blogId));
        modelAndView.addObject("popularTags",blogTagService.find10PopularTags());
        modelAndView.addObject("blogCategoriesMap",blogCategoryService.findAllCategoriesAndCountNumberBlogHasIt());
        modelAndView.addObject("recentBlogs",blogService.findAllRecentBlogs());
        return modelAndView;
    }

    @GetMapping("/suggest")
    @ResponseBody
    public List<String> suggest(@RequestParam(value="term",required = false,defaultValue = "") String term){
       return blogService.findAllByTitle(term)
                .stream()
                .map(blog -> blog.getContent())
                .collect(Collectors.toList());
    }
    @GetMapping("/about")
    public String aboutUs(){
        return "/views-web/about";
    }
}
