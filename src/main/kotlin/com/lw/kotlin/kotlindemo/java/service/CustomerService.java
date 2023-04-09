package com.lw.kotlin.kotlindemo.java.service;

import com.lw.kotlin.kotlindemo.java.model.Customer;
import com.lw.kotlin.kotlindemo.java.repo.CustomerRepo;
import com.lw.kotlin.kotlindemo.java.service.model.CustomerBO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo repo;

    public Customer save(Customer customer) {
        return repo.save(customer);
    }

}
