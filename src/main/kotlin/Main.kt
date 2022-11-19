import dimension.Dimension
import dimension.DimensionFormat
import measurand.*
import quantity.Quantity
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
    val v = 10.msec()

    val dim = Velocity.dimension(Mile, Minute)

    println(v.toString(dim))
}