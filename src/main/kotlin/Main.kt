import measurand.*
import quantity.OutputParameters
import java.util.*

fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 10.meter() + 1.km()
    print(l.toString(OutputParameters(locale = Locale("en", "GB"), fullNameUnit = true)))
}
