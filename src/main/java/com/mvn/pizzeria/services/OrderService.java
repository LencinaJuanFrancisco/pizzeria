package com.mvn.pizzeria.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvn.pizzeria.persistence.entity.OrderEntity;
import com.mvn.pizzeria.persistence.projection.OrderSummary;
import com.mvn.pizzeria.persistence.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    public List<OrderEntity> getAll(){
        return orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrder(String id){
        return orderRepository.findCustomerOrder(id);
    }

    public OrderSummary getSummary(int orderId){
        return orderRepository.findSummary(orderId);
    }
}
