package com.lw.kotlin.kotlindemo.kotlin.controller

import com.lw.kotlin.kotlindemo.kotlin.anno.Slf4j
import com.lw.kotlin.kotlindemo.kotlin.anno.Slf4j.Companion.logger
import com.lw.kotlin.kotlindemo.kotlin.model.KotlinCustomer
import com.lw.kotlin.kotlindemo.kotlin.service.KotlinCustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kotlin/customer")
@Slf4j
class KCustomerController(private val service: KotlinCustomerService) {

    @PostMapping
    fun save(@RequestBody customer: KotlinCustomer) :ResponseEntity<KotlinCustomer> {
        // 你可以使用模板功能，直接使用 $ 来使用，如果室对象属性值，需要使用 ${} 包裹
        logger.info("This is customer:$customer, name is ${customer.name}, address is ${customer.address}")
        val c = service.save(customer)
        return ResponseEntity.ok(c)
    }

    @GetMapping
    fun search() = ResponseEntity.ok(service.search())

}