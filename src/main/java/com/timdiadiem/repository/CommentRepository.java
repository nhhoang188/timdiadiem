package com.timdiadiem.repository;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    Iterable<Comment> findCommentByHotelId(Long id);
    List<Comment> findCommentByBlogId(Long id);
}
