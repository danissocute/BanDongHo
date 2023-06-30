package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface OrderService {
    Orders create(JsonNode orderData);

    Orders findById(Integer id);

    List<Orders> findByUsername(String username);
}
