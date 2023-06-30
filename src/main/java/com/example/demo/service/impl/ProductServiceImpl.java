package com.example.demo.service.impl;

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Products;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;

    @Override
    public Page<Products> findAll(Pageable pageable) {
        return productDAO.findAll(pageable);
    }

    @Override
    public Products findById(Integer id) {
        return productDAO.findById(id).get();
    }

    @Override
    public Page<Products> findByCategoryId(Integer id, Pageable pageable) {
        return productDAO.findByCategoryId(id,pageable);
    }

    @Override
    public Products create(Products products) {
        return productDAO.save(products);
    }

    @Override
    public Products update(Products products) {
        return productDAO.save(products);
    }

    @Override
    public void delete(Integer id) {
        productDAO.deleteById(id);
    }

    @Override
    public List<Products> findAll1() {
        return productDAO.findAll();
    }

}
