package com.timdiadiem.service.impl;

import com.timdiadiem.model.Hotel;
import com.timdiadiem.repository.HotelRepository;
import com.timdiadiem.service.pkInterface.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Page<Hotel> findAll(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Page<Hotel> findAllByNameContaining(String name, Pageable pageable) {
        return hotelRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Page<Hotel> findAllByNamePriceContaining(Double price, Pageable pageable) {
        return hotelRepository.findAllByPrice(price,pageable);
    }

    @Override
    public Page<Hotel> findHotelByPriceLessThanEqual(Double price, Pageable pageable) {
        return hotelRepository.findHotelByPriceLessThanEqual(price,pageable);
    }

    @Override
    public Page<Hotel> findHotelByNameAndPriceContaining(String name, Double price, Pageable pageable) {
        return hotelRepository.findHotelByNameAndPrice(name,price,pageable);
    }
}
