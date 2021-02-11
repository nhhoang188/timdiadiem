package com.timdiadiem.repository;

import com.timdiadiem.model.Booking;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
    void deleteById(Long id);
}
