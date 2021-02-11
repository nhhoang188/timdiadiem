package com.timdiadiem.model;

import javax.persistence.*;

@Entity
@Table( name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "sortdescription")
    private String sortDescription;
    @Column(name = "fulldescription")
    private String fullDescription;
    @Column(name = "convenient")
    private String convenient;

    public Hotel(Long id, String name, String address, String sortDescription, String fullDescription, String convenient) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sortDescription = sortDescription;
        this.fullDescription = fullDescription;
        this.convenient = convenient;
    }

    public Hotel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getConvenient() {
        return convenient;
    }

    public void setConvenient(String convenient) {
        this.convenient = convenient;
    }
}
