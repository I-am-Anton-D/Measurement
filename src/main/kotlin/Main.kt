import measurand.meter
import measurand.second

fun main() {
    val l = 100.meter() / 2.second() / 5.second()
    val z = 100.meter() / 2.second() / 5.second()
    println(l + z)
}