package com.timdiadiem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BlogTag {
    @Id
    private Long id;

    @Length(min=1,max = 10)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "blogTags")
    private Set<Blog> blog;
}
