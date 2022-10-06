
import measurand.*
import quantity.OutputParameters
import units.Prefix


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 5.mile()
    print(l.toString(OutputParameters(prefix = Prefix.KILO, expand = true)))
}
