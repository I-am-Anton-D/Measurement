import measurand.hour
import measurand.km
import measurand.meter


fun main() {
    val length = 10.km() + 5.meter()
    println(length)
    val time = 2.hour()
    println(time)
    val velocity = length / time
    println(velocity)
}