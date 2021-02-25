package com.timdiadiem.repository;

import com.timdiadiem.model.Blog;
import com.timdiadiem.model.BlogCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Modifying
    @Query("UPDATE Blog b SET b.verified = true WHERE b.id = ?1")
    int verifyBlog(Long id);

    @Modifying
    @Query("UPDATE Blog b SET b.views = b.views + 1 WHERE b.id = ?1")
    int updateViewsBlog(Long id);

    List<Blog> findTop3ByOrderByCreatedAtDesc();

    Long countBlogsByBlogCategory(BlogCategory blogCategory);

    @Query(nativeQuery = true,
            value = "SELECT blog.* " +
                    "FROM blog " +
                    "JOIN blog_tags_info ON blog_tags_info.blog_id = blog.id " +
                    "JOIN blog_tag ON blog_tags_info.tag_id = blog_tag.id " +
                    "WHERE blog_tag.id = ?1")
    List<Blog> findAllByBlogTagsOrderByViewsDesc(Long tagId);

    List<Blog> findAllByTitle(String name);

    List<Blog> findAllByCreatedAtOrderByViewsDesc(LocalDate createdAt);

    List<Blog> findAllByBlogCategoryOrderByViewsDesc(Long categoryId);
    Page<Blog> findAllByTitleContaining(String tile, Pageable pageable);
}
