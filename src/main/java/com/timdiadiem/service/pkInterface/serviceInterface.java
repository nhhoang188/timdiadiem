package com.timdiadiem.service.pkInterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface serviceInterface<T> {
    Page<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    void save(T t);

    void delete(T t);

    void deleteById(Long id);

}
