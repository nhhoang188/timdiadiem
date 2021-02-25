package com.timdiadiem.service.impl;

import com.timdiadiem.model.Booking;
import com.timdiadiem.repository.BookingRepository;
import com.timdiadiem.service.pkInterface.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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
    public boolean isAvailable(Long roomid, Date startdate, Date enddate){
        boolean isAvailable = true;
        ArrayList<Booking> bookingList =(ArrayList<Booking>) bookingRepository.findBookingsByRoomId(roomid);
        ArrayList<Date>startdatelist = new ArrayList<>();
        ArrayList<Date>enddatelist = new ArrayList<>();
        for (int i = 0; i < bookingList.size(); i++) {
            startdatelist.add(bookingList.get(i).getStartDate());
        }
        for (int i = 0; i < bookingList.size(); i++) {
            enddatelist.add(bookingList.get(i).getEndDate());
        }
        for (int i = 0; i < bookingList.size(); i++) {
            if((enddate.before(startdatelist.get(i))==true)||(startdate.after(enddatelist.get(i)))==true){
                isAvailable=true;
            }else {
                isAvailable = false;
                break;
            }
        }
        return isAvailable;
    }
}
