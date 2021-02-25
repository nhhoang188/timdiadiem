package com.timdiadiem.controller;

import com.timdiadiem.model.Booking;
import com.timdiadiem.model.Room;
import com.timdiadiem.model.User;
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
    @Autowired
    RoomServiceImpl roomService;
    @Autowired
     BookingServiceImpl bookingService;
    @Autowired
    BankAcountServiceImpl bankAcountService;
    @Autowired
    UserService userService;

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
    public ModelAndView isBookingAvailable(@RequestParam(name = "roomid") String roomid, @RequestParam(name = "startdate")String startdate,@RequestParam(name="enddate")String enddate,@RequestParam("totalprice") String totalprice) throws Exception{
        ModelAndView modelAndView= new ModelAndView();
        boolean isAvailable = true;
        Long roomid1 = Long.parseLong(roomid);
        Double totalprice1 = Double.parseDouble(totalprice);
        Date datestart=new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
        Date dateend = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
       isAvailable= bookingService.isAvailable(roomid1,datestart,dateend);
        if(isAvailable == false){
            String message ="Room not available on dates which you chose. Please choose again";
            modelAndView.setViewName("/views-web/bookingform");
            modelAndView.addObject("message",message);
            Room room = roomService.findById(roomid1).orElse(null);
            modelAndView.addObject("room",room);
            return modelAndView;
        }else {
            Room room = roomService.findById(roomid1).orElse(null);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Booking booking = new Booking(datestart,dateend,totalprice1,user,room);
            bookingService.save(booking);
            modelAndView.setViewName("/views-web/bookingsuccess");
            return modelAndView;
        }
    }

}
