package com.practice.orderservice.controllers;

import com.practice.orderservice.entities.Order;
import com.practice.orderservice.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order-details/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderDetails(id);
    }

}
