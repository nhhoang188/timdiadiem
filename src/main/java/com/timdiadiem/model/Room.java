package com.timdiadiem.model;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer status;
    private String type;
    private String description;
    private Double price;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Room(Long id, Integer status, String type, String description, Double price, Hotel hotel) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.description = description;
        this.price = price;
        this.hotel = hotel;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
