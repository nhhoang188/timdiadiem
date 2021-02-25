package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.BlogCategory;
import com.timdiadiem.model.BlogTag;
import com.timdiadiem.service.impl.BlogAddRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> findAll();
    void verifyBlog(Long id);
    void saveBlog(BlogAddRequest blogAddRequest);
    Blog findById(Long id);
    List<Blog> findByCategory(Long categoryId);
    List<Blog> findAllRecentBlogs();
    Long countBlogsByCategory(BlogCategory blogCategory);
    List<Blog> findByTag(Long tagId);
    List<Blog> findByCreatedAt(LocalDate createdAt);
    Page<Blog> findAll(Pageable pageable);
    List<Blog> findAllByTitle(String title);
    void view(Long id);
    Page<Blog> findAllByTitleContaining(String tile, Pageable pageable);


}
