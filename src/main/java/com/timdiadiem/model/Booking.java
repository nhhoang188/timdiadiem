package com.timdiadiem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "enddate")
    private Date  endDate;
    @Column(name = "totalprice")
    private Double totalPrice;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Booking() {
    }
}
