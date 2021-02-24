package com.timdiadiem.repository;

import com.timdiadiem.model.Booking;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
    void deleteById(Long id);

    List<Booking> findBookingsByRoomId(Long roomid);
}
