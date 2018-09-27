package com.company.introduce.service;

import com.company.introduce.dao.ProductsDao;
import com.company.introduce.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    @Autowired
    ProductsDao productsDao;

    // 列出全部服务列表
    public List<Products> list(){
        return productsDao.findAll();
    }

    // 删除服务
    public void delete(String id){
        productsDao.deleteById(id);
    }

    // 保存服务
    public void save(Products products){
        productsDao.save(products);
    }

    // 查找服务
    public Products findById(String id){
        return productsDao.findById(id).orElse(null);
    }

    // 通过分类查找服务
    public List<Products> findByCategory(String category) {
        return productsDao.findByCategory(category);
    }
}