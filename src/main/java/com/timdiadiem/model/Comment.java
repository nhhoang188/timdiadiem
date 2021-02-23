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
    @NotNull
    private Blog blog;

    public Comment(LocalDateTime timeComment, String content, @NotNull User user, @NotNull Blog blog) {
        this.timeComment = timeComment;
        this.content = content;
        this.user = user;
        this.blog = blog;
    }
}
