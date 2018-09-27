package com.company.introduce.dao;

import com.company.introduce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductsDao extends JpaRepository<Products, String> {
    @Query("from Products p where p.category = :category")
    List<Products> findByCategory(@Param("category") String category);
}
