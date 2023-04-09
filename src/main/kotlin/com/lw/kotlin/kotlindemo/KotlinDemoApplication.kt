package com.lw.kotlin.kotlindemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * seems both the way comment below can not exclude auto config action.
 * If you have some good practice to exclude auto config class, please tell me,thanks a lot.
 */
//@SpringBootApplication(exclude =[SecurityAutoConfiguration::class])
//@ComponentScan(excludeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = [SecurityAutoConfiguration::class])])
@SpringBootApplication
class KotlinDemoApplication

fun main(args: Array<String>) {
	runApplication<KotlinDemoApplication>(*args)
}
