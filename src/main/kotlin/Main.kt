
import measurand.km
import measurand.mile
import quantity.ToStringParameters
import units.Meter
import units.Prefix


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 50.mile() + 5.km()
    print(l.toString(ToStringParameters(unit = Meter, expand = true, prefix = Prefix.KILO)))
}
