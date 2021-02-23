package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.BlogCategory;
import com.timdiadiem.service.impl.BlogAddRequest;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    public void verifyBlog(Long id);
    public void saveBlog(BlogAddRequest blogAddRequest);
    public Blog findById(Long id);
    public List<Blog> findByTag(String tag);
    public List<Blog> findByCategory(String category);
    public List<Blog> findAllRecentBlogs();
    public Long countBlogsByCategory(BlogCategory blogCategory);
}
