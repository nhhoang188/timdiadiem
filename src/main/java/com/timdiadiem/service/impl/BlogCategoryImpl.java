package com.timdiadiem.service.impl;

import com.timdiadiem.model.BlogCategory;
import com.timdiadiem.repository.BlogCategoryRepository;
import com.timdiadiem.service.pkInterface.BlogCategoryService;
import com.timdiadiem.service.pkInterface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class BlogCategoryImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryRepository blogCategoryRepository;

    @Autowired
    private BlogService blogService;

    @Override
    public Map<BlogCategory, Long> findAllCategoriesAndCountNumberBlogHasIt() {
        Map<BlogCategory, Long> blogCategoryAndCountsBlogMap = new LinkedHashMap<>();
        blogCategoryRepository
                .findAll()
                .forEach(blogCategory -> blogCategoryAndCountsBlogMap
                        .put(blogCategory,blogService.countBlogsByCategory(blogCategory)));
        return blogCategoryAndCountsBlogMap;
    }

    @Override
    public void save(BlogCategory blogCategory) {
        blogCategoryRepository.save(blogCategory);
    }


}
