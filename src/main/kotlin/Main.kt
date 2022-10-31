import unit.Inch
import unit.Inch.inch


fun main() {
    //Locale.setDefault(Locale("fr","FR"))

    val l = 2.inch()
    println(l.toString(unit = Inch))

    val l2 = 9.inch() + Inch.ThreeQuarter + Inch.OneQuarter + Inch.SevenEighth
    print(l2.toString(unit = Inch))
}
