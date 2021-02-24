package com.timdiadiem.service.impl;


import com.timdiadiem.model.BlogCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlogAddRequest {
    private String title;
    private String content;
    private String blogTags;
    private BlogCategory blogCategory;
    private String previewImageURL;
    private String previewDescription;

    //TODO : validate
}
