package com.timdiadiem.controller;

import com.timdiadiem.model.*;
import com.timdiadiem.service.email.UserService;
import com.timdiadiem.service.impl.BankAcountServiceImpl;
import com.timdiadiem.service.impl.BookingServiceImpl;
import com.timdiadiem.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private RoomServiceImpl roomService;
    private BookingServiceImpl bookingService;
    private BankAcountServiceImpl bankAcountService;
    private UserService userService;
    @Autowired
    public BookingController(RoomServiceImpl roomService, BookingServiceImpl bookingService, BankAcountServiceImpl bankAcountService, UserService userService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.bankAcountService = bankAcountService;
        this.userService = userService;
    }

    @GetMapping("/getuserandroom")
    public ModelAndView showFormGetRoomandUser(){
        ModelAndView modelAndView = new ModelAndView("/views-web/getuserandroom");
        return modelAndView;
    }
    @GetMapping("/showbookingform")
    public ModelAndView showBookingForm(@RequestParam(name = "roomid") Long roomid){
    Room room = roomService.findById(roomid).orElse(null);
    ModelAndView modelAndView = new ModelAndView("/views-web/bookingform");
//    modelAndView.addObject("user",user1);
    modelAndView.addObject("room",room);
    return modelAndView;
    }
    @GetMapping("/checkavailability")
    public ModelAndView isBookingAvailable(@RequestParam(name = "roomid") String roomid, @RequestParam(name = "startdate")String startdate,@RequestParam(name="enddate")String enddate,@RequestParam("totalprice") Double totalprice) throws Exception{
        ModelAndView modelAndView= null;
        boolean isAvailable = true;
        Long roomid1 = Long.parseLong(roomid);
        Date datestart=new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
        Date dateend = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
       isAvailable= bookingService.isAvailable(roomid1,datestart,dateend);
        if(isAvailable == false){
            String message ="Room not available on dates which you chose. Please choose again";
            modelAndView = new ModelAndView("/views-web/bookingform","message",message);
            return modelAndView;
        }else {
            Room room = roomService.findById(roomid1).orElse(null);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Booking booking = new Booking(datestart,dateend,totalprice,user,room);
            bookingService.save(booking);
            modelAndView = new ModelAndView("/views-web/bookingsuccess");
            return modelAndView;
        }
    }

}
