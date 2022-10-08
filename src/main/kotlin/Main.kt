
import measurand.km
import measurand.mile
import units.Mile


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 5001.mile() + 500.km()

    print(l.toString(unit = Mile))
}
