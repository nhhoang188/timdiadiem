package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.BlogCategory;

import java.util.Map;

public interface BlogCategoryService {
    public Map<BlogCategory,Long> findAllCategoriesAndCountNumberBlogHasIt();
    public void save(BlogCategory blogCategory);
}
