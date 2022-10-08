
import measurand.mile
import units.Mile


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 50.mile()

    print(l.toString(expand = true, unit = Mile))
}
