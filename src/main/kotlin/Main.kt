import measurand.*
import java.util.*

fun main() {
    Locale.setDefault(Locale("en", "GB"))
    val l = 5.km()
    val z = 100.meter() / 2.gram() / 5.second() / 5.gram() * 2.meter()
    println(l)
}