import dimension.Dimension
import measurand.km
import measurand.meter
import unit.length.Mile

fun main() {

    val l = 5.km()
    val z = 1609.344.meter() * 1609.344.meter()
    println(z.toString(Dimension(Mile.pow(2))))
}