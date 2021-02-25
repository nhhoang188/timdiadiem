package com.timdiadiem.repository;

import com.timdiadiem.model.Convenient;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConvenientRepository extends PagingAndSortingRepository<Convenient, Long> {
    void deleteById(Long id);
    Iterable<Convenient> findConvenientByHotelId(Long id);
}
