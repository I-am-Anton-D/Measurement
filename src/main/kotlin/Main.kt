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
    val v = 10000.km() * 1.km()
    val dim = Dimension<Area>(Meter, Meter)
    println(v.toString(Velocity.kmh()));
}