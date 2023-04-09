package com.lw.kotlin.kotlindemo.java.controller;

import com.lw.kotlin.kotlindemo.java.model.Customer;
import com.lw.kotlin.kotlindemo.java.service.CustomerService;
import com.lw.kotlin.kotlindemo.java.service.model.CustomerBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/java/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerBO> save(@Validated @RequestBody CustomerBO customerBO, Errors errors) {

        log.info("Current object is {}", customerBO);
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerBO, customer);

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(customerBO);
        }

        customer = service.save(customer);

        BeanUtils.copyProperties(customer, customerBO);

        return ResponseEntity.ok(customerBO);
    }
}
