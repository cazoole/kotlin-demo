package com.lw.kotlin.kotlindemo.kotlin.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotEmpty

@Entity
data class KotlinCustomer(@Id @GeneratedValue val id: Long) {
    @NotEmpty(message = "Name shouldn't be null!")
    lateinit var name: String

    @NotEmpty(message = "address shouldn't be null!")
    lateinit var address: String
    lateinit var gender: String
}