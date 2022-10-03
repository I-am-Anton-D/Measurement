import measurand.*
import units.Prefix

fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 1.meter() + 3.meter() + 1.km() + 1000.mm()
    print(l.toStringWithFullUnitName(prefix = Prefix.KILO))
}
