
import measurand.Length
import measurand.km
import measurand.mile
import measurand.toMiles
import quantity.OutputParameters
import units.Mile


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 120.km() + 5.mile()
    print(l.toMiles())
}
