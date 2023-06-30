package com.example.demo.service;

import com.example.demo.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Products> findAll(Pageable pageable);

    Products findById(Integer id);

    Page<Products> findByCategoryId(Integer id, Pageable pageable);

    Products create(Products products);

    Products update(Products products);

    void delete(Integer id);

    List<Products> findAll1();
}
