package com.example.demo.dao;

import com.example.demo.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDAO extends JpaRepository<OrderDetails, Integer> {
}
