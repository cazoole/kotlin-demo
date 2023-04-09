import com.lw.kotlin.kotlindemo.kt.cmpn.MachineOperator
import java.lang.RuntimeException

val peter: MachineOperator = MachineOperator("Peter")
peter.checkin()
peter.checkin()
//peter.minimumBreak()  // 会报错，伴生对象属于类级别
println( MachineOperator.minimumBreak())  // 不会报错，可以把伴生看成是静态对象，但是它和java的 static 是有去别的！
//peter.checkedIn  // 会报错
println(MachineOperator.checkedIn )// 可行
peter.checkout()
peter.checkout()

val mark = MachineOperator("Peter")

println(mark same peter)

// ? 用于非空调用  如，peter 不是 null 的时候，调用他的 getName 方法，如果peter.getName 不为 null，则将该值反置
println(peter?.name?.reversed())
println("${(peter?.name as? String)?.length}") // as 作用和 java 的类型转换的作用一样 as? 会将类型转换失败的场景当作null处理

// when 的使用方法
fun nikeName(name: String) = when (name) {
    "Colin" -> "This is me"
    null -> "Joker"
    else -> name.reversed().uppercase() // 因为 null 已经过滤了，所以，else 不会出现空指针问题，不需要使用 ?
        // 注意，when 需要考虑所有的场景，否则，会编译报错。 else 不是必须的，当你前面的分支已经涵括所有的时候，else 可以省略
}

enum class Day {
    WORKDAY, WEEKEND
}

// 当编译器检测到所有的可能都涵括时，else 分支是不需要的，可以省略
fun myDay(day: Day) = when(day) {
    Day.WORKDAY -> "This is work day"
    Day.WEEKEND -> "This is weekend"
}

// 使用 where 进行限定
// 此时，因为任意类型是没有 close 的方法的，所以直接调用 close 方法会编译失败
//fun <T> useAndClose(input: T) {  input.close() }
// 此时我们可以告诉编译器，T包含一个 AutoClosable 接口
fun <T: AutoCloseable> useAndClose(input: T) {input.close()} // 此时就可以了
// 但当我们需要多个类似 AutoClosable 的接口，上述方法就不可以使用了，此时使用 where 即可,前提时对象同时实现了 where 描述的接口
fun <T> useAndCloseMulti(input: T) where T: AutoCloseable, T: Appendable {
    input.append("aaaa")
    input.close()
}

// Java 中有类型擦除，Kotlin 可以使用 reified 来实现类型擦除
abstract class Book(val name:String)
class Fiction(name:String): Book(name)
class NonFiction(name: String):Book(name)
inline fun <reified T> findFirst(books: List<Book>):T {
    val selected = books.filter { book -> book is T }

    if (selected?.size == 0) {
        throw RuntimeException("not found")
    }
    return selected[0] as T
}

class Mailer {
    val details = StringBuilder()
    fun from(addr: String) = details.append("from $addr...\n")
    fun to(addr: String) = details.append("to $addr...\n")
    fun subject(line: String) = details.append("subject $line...\n")
    fun body(message: String) = details.append("body $message...\n")
    fun send() = "...sending...\n$details"
}

fun createMailer() = Mailer()

fun prepareMailer(mailer: Mailer):Unit {
    mailer.run {
        from("builder@agiledeveloper.com")
        to("venkats@agiledeveloper.com")
        subject("Your code suks")
        body("details")
    }
}

fun sendMail(mailer: Mailer): Unit {
    println( mailer.send())
    println("Mail sent")
}

val mailer = createMailer()
prepareMailer(mailer)
sendMail(mailer)

createMailer()
    .also(::prepareMailer)
    .also(::sendMail)


val format = "%-10s%-10s%-10s%-10s"
val str = "context"
val result = "RESULT"

fun toString() = "lexical"

println(String.format("%-10s%-10s%-10s%-10s%-10s",
    "Method", "Argument", "Receiver", "Return", "Result"))
println("===============================================")

// 参数是上下文，结果是 context
val result1 = str.let { arg ->
    print(String.format(format, "let", arg, this, result))
    result // 返回的是 上下文
}

println(result)

// 参数是上下文，返回的是结果
val result2 = str.also { arg ->
    print(String.format(format, "also", arg, this, result))
    result
}
println(result)

val result3 = str.run {
    print(String.format(format, "run", "N/A", this, result))
    result
}
println(result)

val result4 = str.apply {
    print(String.format(format, "apply", "N/A", this, result))
    result
}
println(result)
println(String.format("%-10s", result1))
println(String.format("%-10s", result2))
println(String.format("%-10s", result3))
println(String.format("%-10s", result4))