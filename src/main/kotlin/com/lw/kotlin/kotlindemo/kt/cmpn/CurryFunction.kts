import java.nio.DoubleBuffer
import kotlin.math.sin

/**
 * 函数的 柯里化 Curry 就是将函数进行拆分，例如 f(x)(y) = f(x,y)
 * eg:  f(rate, price) = price / 100 * (100 + rate)
 *      g(price, rate) = price / 100 * (100 + rate)
 *    >> f(rate)(price)  & g(price)(rate)
 */

fun square(n: Int) = n * n
fun triple(n: Int) = n * 3

println(square(triple(2)))

// 聚合函数
fun compose(f: (Int)->Int, g: (Int)->Int): (Int) -> Int = {f(g(it))}

val composeTAndS = compose(::square, ::triple)
println(composeTAndS(2))

// 高阶函数
typealias IntUnaryOp = (Int) -> Int
val compose1:(IntUnaryOp) -> (IntUnaryOp) -> IntUnaryOp = {x -> {y -> {z -> x(y(z))}}}
val square1: IntUnaryOp = {it * it}
val triple1: IntUnaryOp = {it * 3}
val compareTAndS1 = compose1(square1) (triple1)
println(compareTAndS1(3))

// 多态高阶版本
fun <T, U, V> higherCompose() = {f: (U) -> V -> {g: (T) -> U -> {z: T -> f(g(z))}}}
val compose2 = higherCompose<Int, Int, Int> () (square1) (triple1) // Kotlin 此处无法推断，所以需要加上类型定义
println(compose2(3))

// 使 higherCompose(f,g) 和 higherAndThen（g, f) 相同的多态高阶函数

fun <T, U, V> higherAndThen() = {f: (T) -> U -> {g: (U) ->V -> {x: T -> g(f(x))}}}
val compose3 = higherAndThen<Int, Int, Int>()(triple1)(square1)

println("------------------------")
println(compose3(3))

println(sin(2.0))
val cos = higherCompose<Double, Double, Double> ()() { x: Double -> Math.PI / 2 - x }(Math::sin)(2.0)
println(cos)

// 偏函数，就是非值函数，需要一个参数定义才能实现纯函数 （纯函数，类似闭包，不与外界交互）
val rate: Double = 0.2
fun addTex(price: Double): Double = price * (1 + rate) // 因为 rate 可能会改变，所以 addTex 返回值会随着 rate 变动而变动，故为偏函数

// 为了保证值函数，可以将 rate 传入，这样结果就会和输入绑定，不会有多种值
fun addTex1(price: Double, rate: Double) = price * (1 + rate) // 值函数，结果只和传入有关


// 可以根据值函数的特性和偏函数的特性，将偏函数改造成值函数，例如将一个参数当作输入
fun <A, B, C> partialA(a:A, f: (A) -> (B) -> C) = f(a)
//typealias IntUnaryOp = (Int) -> Int
//val compose1:(IntUnaryOp) -> (IntUnaryOp) -> IntUnaryOp = {x -> {y -> {z -> x(y(z))}}}
//val square1: IntUnaryOp = {it * it}
//val triple1: IntUnaryOp = {it * 3}
//val compareTAndS1 = compose1(square1) (triple1)

typealias DoubleUnaryOp = (Double) -> Double
val getRate: DoubleUnaryOp = {1 + it}




// 将第二个参数作为参数构造值函数
fun <A, B, C> partialB(b:B, f:(B) -> (A) -> C) = f(b)


