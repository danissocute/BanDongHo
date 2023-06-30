package com.example.demo.rest.controller;

import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping("")
    public Orders create(@RequestBody JsonNode orderData) {
        return orderService.create(orderData);
    }
}
