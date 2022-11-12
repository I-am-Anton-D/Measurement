import dimension.Dimension
import measurand.*
import unit.Prefix.*
import unit.length.Meter
import unit.length.Mile
import unit.mass.Gram
import unit.time.Hour
import unit.time.Minute
import unit.time.Second


fun main() {
    val v = 1.msec()
    println(v.toString(Area.sqkm()))
}