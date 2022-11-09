import dimension.Dimension
import measurand.*
import unit.length.Meter
import unit.length.Mile
import unit.time.Hour
import unit.time.Minute
import unit.time.Second
import java.util.*

fun main() {

    val l = 5.km()
    val z = 10.meter() / 10.second()
    val v = z.valueIn(Dimension(Mile.pow(1), Minute.pow(-1)))
    println(z.toString(dimension = Dimension(Meter.pow(1), Hour.pow(-1))))
}