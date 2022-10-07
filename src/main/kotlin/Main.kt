
import measurand.mile
import quantity.OutputParameters
import units.Gram


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 5.mile()

    print(l.toString(OutputParameters(unit = Gram::class)))
}
