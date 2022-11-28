import measurand.hour
import measurand.km
import measurand.msec


fun main() {
    val length = 10.km() + 5.km()
    println(length)
    val time = 2.hour()
    println(time)
    val velocity = length / time
    val sum = velocity + 1.msec()
    println(sum)
}