import measurand.*
import quantity.OutputParameters
import units.Prefix

fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 2.km()
    print(l.toString(OutputParameters(fullUnitName = true)))
}
