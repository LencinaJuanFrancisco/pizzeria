package com.mvn.pizzeria.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.mvn.pizzeria.persistence.entity.OrderEntity;
import com.mvn.pizzeria.persistence.projection.OrderSummary;
import com.mvn.pizzeria.services.OrderService;





@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/list")
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }
    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders(){
        return ResponseEntity.ok(orderService.getTodayOrders());
    }
    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }
    @GetMapping("/customerorder/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrder(@PathVariable String id){
        return ResponseEntity.ok(orderService.getCustomerOrder(id));
    }
    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int id){
        return ResponseEntity.ok(orderService.getSummary(id));
    }

}
