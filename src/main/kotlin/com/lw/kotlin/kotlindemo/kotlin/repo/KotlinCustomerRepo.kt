package com.lw.kotlin.kotlindemo.kotlin.repo

import com.lw.kotlin.kotlindemo.kotlin.model.KotlinCustomer
import org.springframework.data.repository.CrudRepository

interface KotlinCustomerRepo: CrudRepository<KotlinCustomer, Long> 