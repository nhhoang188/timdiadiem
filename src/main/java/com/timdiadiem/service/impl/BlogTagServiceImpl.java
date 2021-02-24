package com.timdiadiem.service.impl;

import com.timdiadiem.model.BlogTag;
import com.timdiadiem.repository.BlogTagRepository;
import com.timdiadiem.service.pkInterface.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagRepository blogTagRepository;

    @Override
    public List<BlogTag> findAll() {
        return blogTagRepository.findAll();
    }

    @Override
    public List<BlogTag> find10PopularTags() {
        return blogTagRepository.getPopularTags();
    }
}
