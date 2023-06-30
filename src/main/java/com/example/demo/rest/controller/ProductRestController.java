package com.example.demo.rest.controller;

import com.example.demo.entity.Products;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public List<Products> getAll() {
        return productService.findAll1();
    }

    @GetMapping("/{id}")
    public Products getOne(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping
    public Products create(@RequestBody Products products) {
        return productService.create(products);
    }

    @PutMapping("{id}")
    public Products update(@PathVariable("id") Integer id, @RequestBody Products products) {
        return productService.update(products);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
}
