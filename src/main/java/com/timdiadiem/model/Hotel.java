package com.timdiadiem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
    private List<Room> roomList;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String thumbnail;
}
