package com.example.demo.service.impl;

import com.example.demo.dao.CategoriDAO;
import com.example.demo.entity.Categories;
import com.example.demo.service.CategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriServiceImpl implements CategoriService {
    @Autowired
    CategoriDAO categoriDAO;

    @Override
    public List<Categories> findAll() {
        return categoriDAO.findAll();
    }

}
