package com.practice.orderservice.services.rest;

import com.practice.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
public interface CustomerRestClientService {

    @GetMapping("/customers/{id}?projection=fullCustomer")
    Customer findById(@PathVariable Long id);

    @GetMapping("/customers?projection=fullCustomer")
    PagedModel<Customer> findAll();

}
