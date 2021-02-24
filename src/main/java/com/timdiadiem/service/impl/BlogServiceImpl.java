package com.timdiadiem.service.impl;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.User;
import com.timdiadiem.repository.BlogRepository;
import com.timdiadiem.service.pkInterface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;


    public void verifyBlog(Long id){
        blogRepository.verifyBlog(id);
    }

    @Override
    public void saveBlog(Blog blog){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blog.setUser(user);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

}
