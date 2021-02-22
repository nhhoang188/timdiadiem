package com.timdiadiem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
//    @ManyToMany
//    private BlogTag blogTag;

//    @ManyToOne
//    @JoinColumn("category_id")
//    private BlogCategory blogCategory;
    private Long views;
    private boolean verified;
}
