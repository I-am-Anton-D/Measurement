import measurand.*
import quantity.OutputParameters
import units.Prefix
import java.util.*

fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 2.km()
    print(l.toString(OutputParameters(prefix = Prefix.KILO, locale = Locale("en", "GB"), fullUnitName = true)))
}
