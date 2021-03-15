package com.timdiadiem.repository;

import com.timdiadiem.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TourRepository extends PagingAndSortingRepository<Tour, Long> {
    void deleteById(Long id);
    Page<Tour> findAllByNameContaining(String name, Pageable pageable);
    Page<Tour> findAllByPrice(Double price, Pageable pageable);
    Page<Tour> findTourByPriceLessThanEqual(Double price, Pageable pageable);
    Page<Tour> findTourByNameAndPriceLessThanEqual(String name, Double price , Pageable pageable);
    Page<Tour> findTourByNameAndPrice(String name, Double price , Pageable pageable);
    Page<Tour> findTourByNameAndPriceAndCity_Id(String name, Double price, Long city , Pageable pageable);
    Page<Tour> findTourByCity_Id(Long city, Pageable pageable);
    Page<Tour> findTourByCityId(Long city, Pageable pageable);
}
