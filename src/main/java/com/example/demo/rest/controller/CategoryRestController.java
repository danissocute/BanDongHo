package com.example.demo.rest.controller;

import com.example.demo.entity.Categories;
import com.example.demo.service.CategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
    @Autowired
    CategoriService categoriService;

    @GetMapping()
    public List<Categories> getAll() {
        return categoriService.findAll();
    }
}
