package com.timdiadiem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "blog_tags",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<BlogTag> blogTags;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private BlogCategory blogCategory;
    private Long views = 0l;
    private boolean verified = false;

    public Blog(String title, String content, LocalDateTime createdAt, Set<BlogTag> blogTags, User user, BlogCategory blogCategory) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.blogTags = blogTags;
        this.user = user;
        this.blogCategory = blogCategory;
    }
}
