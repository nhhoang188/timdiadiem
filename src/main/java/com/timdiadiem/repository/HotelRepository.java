package com.timdiadiem.repository;

import com.timdiadiem.model.Hotel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {
}
