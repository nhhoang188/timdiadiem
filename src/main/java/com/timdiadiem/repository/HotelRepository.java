package com.timdiadiem.repository;

import com.timdiadiem.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {
    void deleteById(Long id);
    Page<Hotel> findAllByNameContaining(String name, Pageable pageable);
    Page<Hotel> findAllByPrice(Double price, Pageable pageable);
    Page<Hotel> findHotelByPriceLessThanEqual(Double price, Pageable pageable);
    Page<Hotel> findHotelByNameAndPriceLessThanEqual(String name, Double price , Pageable pageable);
    Page<Hotel> findHotelByNameAndPrice(String name, Double price , Pageable pageable);

}
