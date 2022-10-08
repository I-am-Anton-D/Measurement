
import measurand.km
import measurand.mile
import quantity.OutputParameters


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 5001.mile() + 500.km()

    print(l.toString(OutputParameters()))
}
