package com.lw.kotlin.kotlindemo.kotlin.anno

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Slf4j {

    // 需要使用伴生对象，引为 Kotlin 中室不能使用 @Slf4j 实现与 Java 中一样的功能，所以这里需要使用半生对象来实现这种效果
    companion object {
        val <reified T> T.logger: Logger // 声明一个 Logger 的泛型对象
        inline get() = LoggerFactory.getLogger(T::class.java) // 声明为内联才能使用 T 以便于传递  getLogger
    }
}