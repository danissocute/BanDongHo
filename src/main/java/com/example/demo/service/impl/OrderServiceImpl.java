package com.example.demo.service.impl;

import com.example.demo.dao.OrderDAO;
import com.example.demo.dao.OrderDetailDAO;
import com.example.demo.entity.OrderDetails;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderDetailDAO orderDetailDAO;

    @Override
    public Orders create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Orders orders = mapper.convertValue(orderData, Orders.class);
        orderDAO.save(orders);
        TypeReference<List<OrderDetails>> type = new TypeReference<List<OrderDetails>>() {
        };
        List<OrderDetails> details = mapper.convertValue(orderData.get("orderdetails"), type)
                .stream().peek(d -> d.setOrders(orders)).collect(Collectors.toList());
        orderDetailDAO.saveAll(details);
        return orders;
    }

    @Override
    public Orders findById(Integer id) {
        return orderDAO.findById(id).get();
    }

    @Override
    public List<Orders> findByUsername(String username) {
        return orderDAO.findByUsername(username);
    }
}
