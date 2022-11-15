import dimension.Dimension
import measurand.*
import unit.Prefix.*
import unit.length.Inch
import unit.length.Meter
import unit.length.Mile
import unit.mass.Gram
import unit.time.Hour
import unit.time.Minute
import unit.time.Second
import java.util.*


fun main() {
    val v = 1604.meter() / 1.second()
    val dim = (Mile/ Hour) as Dimension<Velocity>
    println(v.toString(dim))
}