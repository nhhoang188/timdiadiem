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
import java.util.HashMap;
import java.util.List;

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
    public ModelAndView showBookingForm(@RequestParam(name = "roomid") String hotelidstring){
        Long hotelid=Long.parseLong(hotelidstring);
    List<Room> roomList=roomService.findAllByHotel(hotelid);
        HashMap<Long,Double> roomMap = new HashMap<>();
        for (int i = 0; i < roomList.size(); i++) {
            roomMap.put(roomList.get(i).getId(),roomList.get(i).getPrice());
        }
    ModelAndView modelAndView = new ModelAndView("/views-web/bookingform");
//    modelAndView.addObject("user",user1);
    modelAndView.addObject("roomMap",roomMap);
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
