package com.timdiadiem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String thumbnail;
}
