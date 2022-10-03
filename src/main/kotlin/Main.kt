import measurand.*
import java.util.*

fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    var l = 0.01.kilometer() + 5.cm() + 1.km()

    print(l.toStringWithFullUnitName())
}
