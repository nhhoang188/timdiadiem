package com.timdiadiem.service.impl;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.BlogCategory;
import com.timdiadiem.model.BlogTag;
import com.timdiadiem.model.User;
import com.timdiadiem.repository.BlogCategoryRepository;
import com.timdiadiem.repository.BlogRepository;
import com.timdiadiem.repository.BlogTagRepository;
import com.timdiadiem.service.pkInterface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogTagRepository blogTagRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public void verifyBlog(Long id){
        blogRepository.verifyBlog(id);
    }

    @Override
    public void saveBlog(BlogAddRequest blogAddRequest){
        Blog blog = new Blog();
        //        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        blog.setUser(user);
        blog.setCreatedAt(LocalDate.now());
        blog.setBlogCategory(blogAddRequest.getBlogCategory());
        blog.setTitle(blogAddRequest.getTitle());
        blog.setContent(blogAddRequest.getContent());
        blog.setPreviewDescription(blogAddRequest.getPreviewDescription());
        blog.setPreviewImageURL(blogAddRequest.getPreviewImageURL());

        Set<BlogTag> blogTags = new HashSet<BlogTag>();
        Set<String> split = new HashSet<String>(Arrays.asList(blogAddRequest.getBlogTags().split(",")));
        for (String tag: split) {
            boolean tagAlreadyExisted = blogTagRepository.findByName(tag).isPresent();
            if (!tagAlreadyExisted){
                blogTagRepository.save(new BlogTag(tag));
            }
            blogTags.add(blogTagRepository.findByName(tag).get());
        }
        blog.setBlogTags(blogTags);
        blogRepository.save(blog);
    }

    @Override
    public Blog findById(Long id) {
        if (blogRepository.findById(id).isPresent()){
            return blogRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Blog> findByTag(Long tagId) {
        return blogRepository.findAllByBlogTagsOrderByViewsDesc(tagId);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> findByCategory(String category) {
        return null;
    }

    @Override
    public List<Blog> findAllRecentBlogs() {
        return blogRepository.findTop3ByOrderByCreatedAtDesc();
    }

    @Override
    public Long countBlogsByCategory(BlogCategory blogCategory) {
        return blogRepository.countBlogsByBlogCategory(blogCategory);
    }

}
