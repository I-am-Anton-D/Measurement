import dimension.Prefix
import quantity.*
import unit.length.Meter
import unit.length.mile
import unit.temperature.*
import java.math.RoundingMode
import java.text.DecimalFormat


fun main() {
//    val mass = 10.kg()
//    println(mass)
//
//    val length = 1.meter()
//    println(length)
//
//    val time = 1.second()
//    println(time)
//
//    val velocity = length / time
//    println(velocity)
//
//    val acceleration = velocity / 1.second()
//    println(acceleration)
//
//    val force = mass * acceleration
//    println(force)

    val temp = 75.fahrenheit()
    println(temp)
    println(temp.toString(Celsius))
    println(temp.toString(Kelvin))
//
//
//    val length = 10.mile()
//    println(length)
//    println(length.toString(Meter))

    val kevlin = 1000.kelvin()
    println(kevlin)
    println(kevlin.toString(Celsius))

    val celsius = 0.celsius()
    println(celsius)
    println(celsius.toString(Kelvin))

}