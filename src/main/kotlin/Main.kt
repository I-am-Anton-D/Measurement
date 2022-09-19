import measurand.*
import units.Prefix
import java.util.Locale

fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    var l = 0.01.kilometer() + 5
    val l2 = Length(10)
    val v = l2 valueIn Prefix.DEKA
    println(v)
    print(l)
}
