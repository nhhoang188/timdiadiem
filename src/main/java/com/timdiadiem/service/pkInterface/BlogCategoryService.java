package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.BlogCategory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BlogCategoryService {
    public List<BlogCategory> findAll();
}
