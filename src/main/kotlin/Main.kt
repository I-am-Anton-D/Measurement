import measurand.foot
import measurand.inch

fun main() {

    val l = 2.foot() + 3.inch()
    println(l.toString(expand = true, normalize = false))
}