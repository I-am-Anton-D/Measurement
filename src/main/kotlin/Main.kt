import measurand.*
import units.Prefix
import java.util.Locale

fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    var l = 0.01.kilometer() + 5.cm()
    print(l)
}
