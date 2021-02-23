package com.timdiadiem.controller;

import com.timdiadiem.service.impl.BlogAddRequest;
import com.timdiadiem.service.pkInterface.BlogCategoryService;
import com.timdiadiem.service.pkInterface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCategoryService blogCategoryService;

    @GetMapping("/add")
    public ModelAndView showAddNewBlogForm(){
        return new ModelAndView("views-web/blog-post","blogCategories",blogCategoryService.findAllCategoriesAndCountNumberBlogHasIt());
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
        modelAndView.addObject("blogCategoriesMap",blogCategoryService.findAllCategoriesAndCountNumberBlogHasIt());
        modelAndView.addObject("recentBlogs",blogService.findAllRecentBlogs());
        return modelAndView;
    }

}
