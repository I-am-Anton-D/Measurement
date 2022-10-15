
import measurand.km
import util.ToStringParameters
import unit.Meter
import util.Prefix


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 2.km()
    print(l.toString(ToStringParameters(unit = Meter, expand = true, prefix = Prefix.KILO)))
}
