package com.company.introduce.service;

import com.company.introduce.dao.ArticleDao;
import com.company.introduce.dao.UserDao;
import com.company.introduce.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    // 列出全部文章
    public List<Article> list(){
        return articleDao.findAll();
    }

    // 删除文章
    public void delete(String id){
        articleDao.deleteById(id);
    }

    // 保存文章
    public void save(Article article){
        articleDao.save(article);
    }

    // 查找文章
    public Article findById(String id){
        return articleDao.findById(id).orElse(null);
    }

    // 通过分类查找文章
    public List<Article> findByCategory(String category) {
        return articleDao.findByCategory(category);
    }
}
