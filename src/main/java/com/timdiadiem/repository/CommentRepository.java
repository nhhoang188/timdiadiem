package com.timdiadiem.repository;

import com.timdiadiem.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    Iterable<Comment> findCommentByHotelId(Long id);
}
