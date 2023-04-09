package com.lw.kotlin.kotlindemo.kotlin.service

import com.lw.kotlin.kotlindemo.kotlin.model.KotlinCustomer
import com.lw.kotlin.kotlindemo.kotlin.repo.KotlinCustomerRepo
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class KotlinCustomerService(private val kotlinCustomerRepo: KotlinCustomerRepo) {

    fun save(customer: KotlinCustomer): KotlinCustomer {
        return kotlinCustomerRepo.save(customer)
    }

    fun search(): Iterable<KotlinCustomer> {
        return kotlinCustomerRepo.findAll()
    }
}