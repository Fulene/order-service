package com.practice.orderservice.services.rest;

import com.practice.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface InventoryRestClientService {

    @GetMapping("/products/{id}?projection=fullProduct")
    Product findById(@PathVariable Long id);

    @GetMapping("/products?projection=fullProduct")
    PagedModel<Product> findPage();

}
