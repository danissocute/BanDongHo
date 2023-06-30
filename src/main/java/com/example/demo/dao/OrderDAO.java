package com.example.demo.dao;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Orders, Integer> {
    @Query("select p from Orders p where p.accounts.username=?1")
    List<Orders> findByUsername(String username);
}
