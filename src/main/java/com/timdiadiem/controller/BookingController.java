package com.timdiadiem.controller;

import com.timdiadiem.model.Booking;
import com.timdiadiem.model.Room;
import com.timdiadiem.model.User;
import com.timdiadiem.service.email.UserService;
import com.timdiadiem.service.impl.BankAcountServiceImpl;
import com.timdiadiem.service.impl.BookingServiceImpl;
import com.timdiadiem.service.impl.RoomServiceImpl;
import com.timdiadiem.service.pkInterface.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    HotelService hotelService;
    @GetMapping("/getuserandroom")
    public ModelAndView showFormGetRoomandUser(){
        ModelAndView modelAndView = new ModelAndView("/views-web/getuserandroom");
        return modelAndView;
    }
    @GetMapping("/showbookingform")
    public ModelAndView showBookingForm(@RequestParam(name = "roomid") String hotelidstring, Principal principal){
        Long hotelid=Long.parseLong(hotelidstring);
    List<Room> roomList=roomService.findAllByHotel(hotelid);
        HashMap<Long,Double> roomMap = new HashMap<>();
        for (int i = 0; i < roomList.size(); i++) {
            roomMap.put(roomList.get(i).getId(),roomList.get(i).getPrice());
        }
    ModelAndView modelAndView = new ModelAndView("/views-web/bookingform");
        if (principal != null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject("user", user);
        }
        modelAndView.addObject("hotel", hotelService.findById(hotelid));
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
