import measurand.hour
import measurand.km
import measurand.msec
import unit.length.mile


fun main() {
    val length = 10.mile()
    println(length)
    val time = 2.hour()
    println(time)
    val velocity = length / time
    val sum = velocity
    println(sum)
}