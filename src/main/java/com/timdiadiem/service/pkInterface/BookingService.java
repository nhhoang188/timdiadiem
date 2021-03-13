package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Booking;

import java.util.List;

public interface BookingService extends serviceInterface<Booking> {
    List<Booking> findBookingsByUserId(Long roomid);
}
