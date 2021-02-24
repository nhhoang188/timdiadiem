package com.timdiadiem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BlogCategory {
    @Id
    private Long id;

    @NotBlank
    private String name;

    public BlogCategory(@NotBlank String name) {
        this.name = name;
    }
}
