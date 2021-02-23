package com.timdiadiem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private String name;
    private Double price;
    @Column(name = "sortdescription")
    private String sortDescription;
    @Column(name = "fulldescription")
    private String fullDescription;
    @Column(name = "startdate")
    private Date startDate;
    private Integer days;

    public Tour() {
    }

    public Tour(Long id, Room room, String name, Double price, String sortDescription, String fullDescription, Date startDate, Integer days) {
        this.id = id;
        this.room = room;
        this.name = name;
        this.price = price;
        this.sortDescription = sortDescription;
        this.fullDescription = fullDescription;
        this.startDate = startDate;
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
