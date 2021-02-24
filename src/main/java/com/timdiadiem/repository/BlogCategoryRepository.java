package com.timdiadiem.repository;

import com.timdiadiem.model.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory,Long> {
}
