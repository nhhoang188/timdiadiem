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
import java.util.List;

@Repository
@Transactional
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Modifying
    @Query("UPDATE Blog b SET b.verified = true WHERE b.id = ?1")
    public int verifyBlog(Long id);

    @Modifying
    @Query("UPDATE Blog b SET b.views = ?1 WHERE b.id = ?2")
    public int updateViewsBlog(Long views, Long id);

    public List<Blog> findTop3ByOrderByCreatedAtDesc();

    public Long countBlogsByBlogCategory(BlogCategory blogCategory);

    @Query(nativeQuery = true,
            value = "SELECT blog.* " +
                    "FROM blog " +
                    "JOIN blog_tags_info ON blog_tags_info.blogid = blog.id " +
                    "JOIN blog_tag ON blog_tags_info.tag_id = blog_tag.id " +
                    "WHERE blog_tag.id = ?1")
    public List<Blog> findAllByBlogTagsOrderByViewsDesc(Long tagId);

    public List<Blog> findAllByTitle(String name);

}
