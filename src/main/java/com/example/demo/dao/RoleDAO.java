package com.example.demo.dao;

import com.example.demo.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Roles, Integer> {
}
