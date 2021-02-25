package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService extends serviceInterface<Hotel> {
    Page<Hotel> findAllByNameContaining(String name, Pageable pageable);
    Page<Hotel> findAllByNamePriceContaining(Double price, Pageable pageable);
    Page<Hotel> findHotelByPriceLessThanEqual(Double price, Pageable pageable);
    Page<Hotel> findHotelByNameAndPriceContaining(String name, Double price , Pageable pageable);
}
