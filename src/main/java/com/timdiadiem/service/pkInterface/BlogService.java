package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Blog;

import java.util.Optional;

public interface BlogService {
    public void verifyBlog(Long id);
    public void saveBlog(Blog blog);
    public Optional<Blog> findById(Long id);
}
