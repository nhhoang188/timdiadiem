package com.timdiadiem.repository;

import com.timdiadiem.model.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BlogTagRepository extends JpaRepository<BlogTag,Long> {
    public Optional<BlogTag> findByName(String name);
    @Query(nativeQuery = true,value = "" +
            "SELECT blog_tag.* " +
            "FROM blog_tag " +
            "JOIN blog_tags_info " +
            "ON blog_tag.id = blog_tags_info.tag_id " +
            "GROUP BY blog_tag.id " +
            "ORDER BY COUNT(blog_tags_info.blog_id) " +
            "DESC LIMIT 10 ")
    public List<BlogTag> getPopularTags();
}
