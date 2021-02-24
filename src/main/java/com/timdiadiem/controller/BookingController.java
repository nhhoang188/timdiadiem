package com.timdiadiem.controller;

import com.timdiadiem.model.*;
import com.timdiadiem.service.impl.BankAcountServiceImpl;
import com.timdiadiem.service.impl.BookingServiceImpl;
import com.timdiadiem.service.impl.RoomServiceImpl;
import com.timdiadiem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private RoomServiceImpl roomService;
    private UserServiceImpl userService;
    private BookingServiceImpl bookingService;
    private BankAcountServiceImpl bankAcountService;
    @Autowired
    public BookingController(RoomServiceImpl roomService, UserServiceImpl userService, BookingServiceImpl bookingService, BankAcountServiceImpl bankAcountService) {
        this.roomService = roomService;
        this.userService = userService;
        this.bookingService = bookingService;
        this.bankAcountService = bankAcountService;
    }







    @GetMapping("/getuserandroom")
    public ModelAndView showFormGetRoomandUser(){
        ModelAndView modelAndView = new ModelAndView("/views-web/getuserandroom");
        return modelAndView;
    }
    @GetMapping("/showbookingform")
    public ModelAndView showBookingForm(@RequestParam(name = "roomid") Long roomid,@RequestParam(name = "userid") Long userid){

    Room room = new Room(1L,1,"vip","viewbien",1000D,new Hotel());
    roomService.save(room);
    BankAcount bankAcount = new BankAcount(1L,"VCB",30000D);
    bankAcountService.save(bankAcount);
//        User user = userService.findById(1L).orElse(null);
//        user.setEnabled(true);
//        user.setLocked(true);
//        user.setBankAcount(bankAcount);
//        userService.save(user);
//    User user1 = userService.findById(userid).orElse(null);
    Room room1 = roomService.findById(roomid).orElse(null);
    ModelAndView modelAndView = new ModelAndView("/views-web/bookingform");
//    modelAndView.addObject("user",user1);
    modelAndView.addObject("room",room1);
    return modelAndView;
    }
    @GetMapping("/checkavailability")
    public boolean isBookingAvailable(@RequestParam(name = "roomid") String roomid, @RequestParam(name = "startdate")String startdate,@RequestParam(name="enddate")String enddate) throws Exception{
        boolean isAvailable = true;
        Long roomid1 = Long.parseLong(roomid);
        Date datestart=new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
        Date dateend = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
       isAvailable= bookingService.isAvailable(roomid1,datestart,dateend);
        return isAvailable;
    }
}
