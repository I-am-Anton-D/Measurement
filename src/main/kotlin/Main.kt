import measurand.gram
import measurand.km
import measurand.meter
import measurand.mile
import quantity.OutputParameters
import java.math.BigDecimal
import java.text.DecimalFormat


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 2.mile()

    print(l)
}
