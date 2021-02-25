package com.timdiadiem.controller;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.Comment;
import com.timdiadiem.model.Hotel;
import com.timdiadiem.model.User;
import com.timdiadiem.service.impl.BlogAddRequest;
import com.timdiadiem.service.pkInterface.BlogCategoryService;
import com.timdiadiem.service.pkInterface.BlogService;
import com.timdiadiem.service.pkInterface.BlogTagService;
import com.timdiadiem.service.pkInterface.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    CommentService commentService;

    @GetMapping
    public ModelAndView showBlogsPage(@PageableDefault(size = 3) Pageable pageable, @RequestParam("name") Optional<String> name) {
        Page<Blog> blogs;
        if (name.isPresent()){
            blogs = blogService.findAllByTitleContaining(name.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/views-web/blog");
        modelAndView.addObject("listBlogs", blogs);
        return modelAndView;
    }

    @GetMapping("/searchbytag")
    public ModelAndView showListBlogsByTag(@RequestParam("tagid") Long tagId) {
        return new ModelAndView("/views-web/blog", "listBlogs", blogService.findByTag(tagId));
    }

    @GetMapping("/searchbydaycreate")
    public ModelAndView showListBlogsByCreatedAt(@RequestParam("createdat") String createdAt) {
        return new ModelAndView("/views-web/blog", "listBlogs", blogService.findByCreatedAt(LocalDate.parse(createdAt)));
    }

    @GetMapping("/searchbycategory")
    public ModelAndView showListBlogsByCategory(@RequestParam("categoryid") Long categoryId) {
        return new ModelAndView("/views-web/blog", "listBlogs", blogService.findByCategory(categoryId));
    }

    @GetMapping("/add")
    public ModelAndView showAddNewBlogForm() {
        return new ModelAndView("views-web/blog-post", "blogCategories", blogCategoryService.findAll());
    }

    @PostMapping("/add")
    public void postBlog(@RequestBody BlogAddRequest blogAddRequest) {
        blogService.saveBlog(blogAddRequest);
    }

    @GetMapping("/{blogId}")
    public ModelAndView showBlog(@PathVariable Long blogId, Principal principal) {
        if (blogService.findById(blogId) == null) {
            return new ModelAndView("/views-error/blog-not-found");
        }
        blogService.view(blogId);
        ModelAndView modelAndView = new ModelAndView("/views-web/blog-single");
        modelAndView.addObject("blog", blogService.findById(blogId));
        modelAndView.addObject("popularTags", blogTagService.find10PopularTags());
        modelAndView.addObject("blogCategoriesMap", blogCategoryService.findAllCategoriesAndCountNumberBlogHasIt());
        modelAndView.addObject("recentBlogs", blogService.findAllRecentBlogs());
//        modelAndView.addObject("listcomment", commentService.findCommentByBloglId(blogId));
//        modelAndView.addObject("comment", new Comment());
        if (principal != null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }

    @PostMapping("/{blogId}")
    public ModelAndView postCmt(@ModelAttribute("comment") Comment comment) {
        commentService.save(comment);
        return new ModelAndView("redirect:/blogs");
    }
//    @GetMapping("/suggest")
//    @ResponseBody
//    public List<String> suggest(@RequestParam(value="term",required = false,defaultValue = "") String term){
//       return blogService.findAllByTitle(term)
//                .stream()
//                .map(blog -> blog.getContent())
//                .collect(Collectors.toList());
//    }
}
