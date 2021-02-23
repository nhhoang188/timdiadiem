package com.timdiadiem.repository;

import com.timdiadiem.model.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface BlogTagRepository extends JpaRepository<BlogTag,Long> {
    public Optional<BlogTag> findByName(String name);
}
