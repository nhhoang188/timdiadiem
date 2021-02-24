package com.timdiadiem.repository;

import com.timdiadiem.model.Tour;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TourRepository extends PagingAndSortingRepository<Tour, Long> {
    void deleteById(Long id);
}
