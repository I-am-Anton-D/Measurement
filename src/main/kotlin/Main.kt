import measurand.gram
import measurand.kilometer
import measurand.meter

fun main() {

    var l = 0.01.kilometer() + 5.meter()
    var m = 5.gram()
    var m2 = 50.gram()
    var sum =m + m2;


    print(sum.toString(fullUnitName = true))
}
