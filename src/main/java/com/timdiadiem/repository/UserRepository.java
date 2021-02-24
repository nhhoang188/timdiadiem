package com.timdiadiem.repository;

import com.timdiadiem.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    void deleteById(Long id);
}
