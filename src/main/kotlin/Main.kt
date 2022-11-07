import measurand.gram
import measurand.meter
import measurand.second
import java.util.*

fun main() {
    Locale.setDefault(Locale("en", "GB"))
    val l = 100.meter() / 2.second() / 5.gram() / 5.gram() * 2.meter()
    val z = 100.meter() / 2.gram() / 5.second() / 5.gram() * 2.meter()
    println(l + z)
}