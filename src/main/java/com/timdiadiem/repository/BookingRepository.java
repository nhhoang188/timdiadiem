package com.timdiadiem.repository;

import com.timdiadiem.model.Booking;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
    void deleteById(Long id);

    List<Booking> findBookingsByRoomId(Long roomid);
    List<Booking> findBookingsByUserId(Long roomid);
}
