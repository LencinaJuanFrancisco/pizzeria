package com.mvn.pizzeria.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvn.pizzeria.persistence.entity.CustomerEntity;
import com.mvn.pizzeria.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping("/find/{phone}")
    public ResponseEntity<CustomerEntity> getCustomerByPhone(@PathVariable String phone){
            return ResponseEntity.ok(customerService.getCustomerByPhone(phone));
    }
}
