package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService extends serviceInterface<Comment> {
    Iterable<Comment> findCommentByHotelId(Long id);
    List<Comment> findCommentByBloglId(Long id);
}
