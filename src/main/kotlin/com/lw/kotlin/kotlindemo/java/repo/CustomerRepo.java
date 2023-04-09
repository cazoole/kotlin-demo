package com.lw.kotlin.kotlindemo.java.repo;

import com.lw.kotlin.kotlindemo.java.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
}
