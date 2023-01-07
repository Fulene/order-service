package com.practice.orderservice.services;

import com.practice.orderservice.entities.Order;
import com.practice.orderservice.entities.ProductItem;
import com.practice.orderservice.entities.enums.OrderStatus;
import com.practice.orderservice.model.Product;
import com.practice.orderservice.repositories.OrderRepository;
import com.practice.orderservice.repositories.ProductItemRepository;
import com.practice.orderservice.services.rest.CustomerRestClientService;
import com.practice.orderservice.services.rest.InventoryRestClientService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClientService customerRestClientService;
    private final InventoryRestClientService inventoryRestClientService;

    public OrderService(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    public void populateDb() {
        var customers = customerRestClientService.findPage().getContent().stream().toList();
        var products = inventoryRestClientService.findPage().getContent().stream().toList();
        var random = new Random();

        for (int i = 0; i < 20; i++) {
            var order = Order.builder()
                .customerId(customers.get(random.nextInt(customers.size())).getId())
                .status(Math.random() > .5 ? OrderStatus.DELIVERED : OrderStatus.CREATED)
                .createdDate(new Date())
                .build();

            var savedOrder = orderRepository.save(order);

            for (Product product : products) {
                if (Math.random() > .7) {
                    var productItem = ProductItem.builder()
                        .order(savedOrder)
                        .productId(product.getId())
                        .price(product.getPrice())
                        .quantity(1 + random.nextInt(10))
                        .discount(Math.random() * 10)
                        .build();

                    productItemRepository.save(productItem);
                }
            }
        }

    }

    public Collection<Order> findAll() {
        return orderRepository.findAll();
    }

}
