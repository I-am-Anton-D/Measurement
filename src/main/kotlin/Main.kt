import unit.length.Foot
import unit.length.Foot.foot
import unit.length.Inch.inch


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 2.foot() + 6.inch()
    println(l.toString(unit = Foot))
}
