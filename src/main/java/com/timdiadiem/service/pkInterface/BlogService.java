package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.BlogCategory;
import com.timdiadiem.model.BlogTag;
import com.timdiadiem.service.impl.BlogAddRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface BlogService {
    public List<Blog> findAll();
    public void verifyBlog(Long id);
    public void saveBlog(BlogAddRequest blogAddRequest);
    public Blog findById(Long id);
    public List<Blog> findByCategory(String category);
    public List<Blog> findAllRecentBlogs();
    public Long countBlogsByCategory(BlogCategory blogCategory);
    public List<Blog> findByTag(Long tagId);
    public Page<Blog> findAll(Pageable pageable);
    public List<Blog> findAllByTitle(String title);
}
