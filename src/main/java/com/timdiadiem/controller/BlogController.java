package com.timdiadiem.controller;

import com.timdiadiem.model.Blog;
import com.timdiadiem.service.pkInterface.BlogCategoryService;
import com.timdiadiem.service.pkInterface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCategoryService blogCategoryService;
    @GetMapping
    public ModelAndView blog(){
        return new ModelAndView("views-web/blog");
    }
    @GetMapping("/add")
    public ModelAndView showAddNewBlogForm(){
        return new ModelAndView("views-web/blog-post","blogCategories",blogCategoryService.findAll());
    }

    @PostMapping("/add")
    public void postBlog(@RequestBody Blog blog){
//        blogService.saveBlog(blog);
        System.out.println("posted");
    }
    @GetMapping("/{blogId}")
    public ModelAndView showBlog(@PathVariable Long blogId){
        return new ModelAndView("/views-web/blog-single","blog", blogService.findById(blogId));
    }

}
