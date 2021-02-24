package com.timdiadiem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    private Long id;

    @NotNull
    private LocalDateTime timeComment;

    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Comment(Long id, @NotNull LocalDateTime timeComment, @NotBlank String content, @NotNull User user, Blog blog, Hotel hotel) {
        this.id = id;
        this.timeComment = timeComment;
        this.content = content;
        this.user = user;
        this.blog = blog;
        this.hotel = hotel;
    }
}
