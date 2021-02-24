package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.BlogTag;

import java.util.List;

public interface BlogTagService {
    public List<BlogTag> findAll();
    public List<BlogTag> find10PopularTags();
}
