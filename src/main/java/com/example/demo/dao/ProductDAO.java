package com.example.demo.dao;

import com.example.demo.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Products, Integer> {
    @Query("select p from Products p where p.categories.id=?1")
    Page<Products> findByCategoryId(Integer id, Pageable pageable);
}
