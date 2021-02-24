package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.BlogCategory;

import java.util.List;
import java.util.Map;

public interface BlogCategoryService {
    public List<BlogCategory> findAll();
    public Map<BlogCategory,Long> findAllCategoriesAndCountNumberBlogHasIt();
    public void save(BlogCategory blogCategory);
}
