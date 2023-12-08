package com.mvn.pizzeria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvn.pizzeria.persistence.entity.CustomerEntity;
import com.mvn.pizzeria.persistence.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity getCustomerByPhone(String phone){

        return customerRepository.findByPhone(phone);
    }
}
