import dimension.Dimension
import dimension.DimensionFormat
import measurand.*
import quantity.Quantity
import unit.Prefix.*
import unit.length.Inch
import unit.length.Meter
import unit.length.Mile
import unit.length.mile
import unit.mass.Gram
import unit.time.Hour
import unit.time.Minute
import unit.time.Second
import java.util.*


fun main() {
    val v = 1.kmh() + 1.msec()

    println(v)
}