package com.timdiadiem.service.impl;

import com.timdiadiem.model.Booking;
import com.timdiadiem.repository.BookingRepository;
import com.timdiadiem.service.pkInterface.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Override
    public Page<Booking> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void save(Booking booking) {
    bookingRepository.save(booking);
    }

    @Override
    public void delete(Booking booking) {
    bookingRepository.delete(booking);
    }

    @Override
    public void deleteById(Long id) {
    bookingRepository.deleteById(id);
    }
}
