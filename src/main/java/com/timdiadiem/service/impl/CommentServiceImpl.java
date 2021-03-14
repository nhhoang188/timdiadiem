package com.timdiadiem.service.impl;

import com.timdiadiem.model.Comment;
import com.timdiadiem.repository.CommentRepository;
import com.timdiadiem.service.pkInterface.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        comment.setTimeComment(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void deleteById(Long id) {
    commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findCommentByHotelId(Long id) {
        return commentRepository.findCommentByHotelId(id);
    }

    @Override
    public Iterable<Comment> findCommentByTourId(Long id) {
        return commentRepository.findCommentByTourId(id);
    }

    @Override
    public List<Comment> findCommentByBloglId(Long id) {
        return commentRepository.findCommentByBlogId(id);
    }
}
