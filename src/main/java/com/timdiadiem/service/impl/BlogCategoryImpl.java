package com.timdiadiem.service.impl;

import com.timdiadiem.model.BlogCategory;
import com.timdiadiem.repository.BlogCategoryRepository;
import com.timdiadiem.service.pkInterface.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCategoryImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryRepository blogCategoryRepository;
    @Override
    public List<BlogCategory> findAll() {
        return blogCategoryRepository.findAll();
    }
}
