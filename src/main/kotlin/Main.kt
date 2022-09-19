import measurand.gram
import measurand.kilometer
import measurand.meter
import java.util.Locale

fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    var l = 0.01.kilometer() + 5.meter()
    var m = 5.gram()
    var m2 = 50.gram()
    var sum =100.gram();

    print(l.toString(unitFullName = true))
}
