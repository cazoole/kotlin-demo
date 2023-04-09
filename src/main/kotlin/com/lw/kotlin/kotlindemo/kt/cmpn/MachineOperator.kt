package com.lw.kotlin.kotlindemo.kt.cmpn

import com.lw.kotlin.kotlindemo.kotlin.anno.Slf4j
import com.lw.kotlin.kotlindemo.kotlin.anno.Slf4j.Companion.logger

/**
 * 本例用来讲述伴生对象的概念和使用案例
 *
 * 伴生对象： 一般属于类级别，且不属于类的特定实例上需要的属性和方法，一般使用伴生对象的方式处理。伴生对象室类中定义的单例，可以实现接口，
 *  也可以从基类扩展。
 *  伴生对象也可以做初始化的工作，例如 对象使用前需要就想要初始化的内容，例如，你想要在启动服务就需要缓存数据，这就可以使用伴生对象来实现
 *
 *  将类的构造函数设置为 private， 可以使用伴生对象来创建一个单例实例也是可行的。类似的功能可以使用 @JvmStatic
 *  class MachineOperator private constructor(val name: String) {
 *      companion object {
 *          fun create(name: String): MachineOperator {
 *              val instance = MachineOperator(name)
 *              instance.checkin()
 *              return instance
 *          }
 *      }
 *  }
 *
 */
@Slf4j
class MachineOperator (val name: String){

    fun checkin() {
        checkedIn++
        logger.info("$name is checkin now, the current checkedIn is $checkedIn")
    }
    fun checkout() {
        checkedIn--
        logger.info("$name is checkout now, the current checkedIn is $checkedIn")
    }

    companion object { // 可以使用别名，例如 companion object MachineOperatorFactory 来实例化更容易使用
        var checkedIn = 0

        fun minimumBreak() = "15 minutes every 2 hours"
    }

    /**
     * 中缀表达式  infix 定义一个中缀表达式，这个方法可以使用 obj.methodName(other: Any) 的方式调用
     * 也可以使用 obj methodName other 的方式调用
     * 你可以定义多种中缀方法，然后用空格隔开来调用
     */
    infix fun same(other: MachineOperator) = this.name == other.name
}
