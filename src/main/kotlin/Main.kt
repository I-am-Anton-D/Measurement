import measurand.day
import measurand.hour
import measurand.second


fun main() {

    val l = 2.day() + 12.hour() + 12.255.second()
    println(l.toString(expand = true))
}