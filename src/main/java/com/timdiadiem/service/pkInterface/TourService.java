package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TourService extends serviceInterface<Tour> {
    Page<Tour> findAllByNameContaining(String name, Pageable pageable);
    Page<Tour> findAllByPrice(Double price, Pageable pageable);
    Page<Tour> findTourByPriceLessThanEqual(Double price, Pageable pageable);
    Page<Tour> findTourByNameAndPriceLessThanEqual(String name, Double price , Pageable pageable);
    Page<Tour> findTourByNameAndPrice(String name, Double price , Pageable pageable);
}
