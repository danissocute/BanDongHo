package com.example.demo.dao;

import com.example.demo.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriDAO extends JpaRepository<Categories, Integer> {
}
