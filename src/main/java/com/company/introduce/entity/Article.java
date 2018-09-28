package com.company.introduce.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "int")
    private String id;

    // 文章标题
    @Column(name = "title")
    private String title;

    // 文章作者
    @Column(name = "author", columnDefinition = "varchar(64)")
    private String author;

    // 文章分类
    @Column(name = "category", columnDefinition = "varchar(64)")
    private String category;

    // 文章正文
    @Column(name = "content", columnDefinition = "text")
    private String content;

    // 文章中图片资源路径
    @Column(name = "picture", columnDefinition = "text")
    private String picture;

    // 访问量
    @Column(name = "views", columnDefinition = "int")
    private String views;

    // 发表时间
    @Column(name = "date", columnDefinition = "date")
    private String date;

    public int getId() {
        return Integer.parseInt(id);
    }

    public void setId(int id) {
        this.id = id+"";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getViews() {
        return Integer.parseInt(views);
    }

    public void setViews(int views) {
        this.views = views+"";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
