package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Comment;
import org.springframework.stereotype.Service;


public interface CommentService extends serviceInterface<Comment> {
    Iterable<Comment> findCommentByHotelId(Long id);
}
