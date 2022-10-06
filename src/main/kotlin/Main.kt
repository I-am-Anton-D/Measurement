
import measurand.*
import quantity.OutputParameters
import units.Gram
import units.Mile


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 5.mile()
    print(l.toString(OutputParameters(unit = Gram::class)))
}
