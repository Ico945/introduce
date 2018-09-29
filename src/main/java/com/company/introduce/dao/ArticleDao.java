package com.company.introduce.dao;

import com.company.introduce.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article, String> {
    @Query("from Article a where a.title = :title")
    Article findByTitle(@Param("title") String title);

    @Transactional
    @Modifying
    @Query("update Article a set views=views+1 where a.id = :id")
    void updateViews(@Param("id") String id);
}
