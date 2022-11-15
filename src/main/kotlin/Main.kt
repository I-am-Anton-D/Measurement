import dimension.Dimension
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
    val v = 1.meter() * 1.gram()
    val dim = Dimension<Quantity>(Gram.pow(1, prefix = KILO), Hour.pow(-1))

    println(v.toString(dim))
}