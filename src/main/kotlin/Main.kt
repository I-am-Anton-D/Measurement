import measurand.*
import java.util.*

fun main() {

    val l = 5.km()
    val z = 100.meter() / 2.gram() / 5.second() / 5.gram() * 2.meter()
    println(z.toString(Locale("en", "GB")))
}