import dimension.Prefix
import extension.toAnsiString
import extension.toDecomposedString
import quantity.*
import unit.length.Meter
import unit.length.mile
import unit.temperature.*
import unit.time.Day
import unit.time.Hour
import unit.time.Minute
import java.math.RoundingMode
import java.text.DecimalFormat


fun main() {
    val mass = 10.kg()
    println(mass)

    val length = 1.meter()
    println(length)

    val time = 1.second()
    println(time)

    val velocity = length / time
    println(velocity)

    val acceleration = velocity / 1.second()
    println(acceleration.toAnsiString())

    val force = mass * acceleration
    println(force)

    val temp = 75.fahrenheit()
    println(temp)
    println(temp.toString(Celsius))
    println(temp.toString(Kelvin))


    val length2 = 10.mile()
    println(length2)
    println(length2.toString(Meter))

    val kevlin = 1000.kelvin()
    println(kevlin)
    println(kevlin.toString(Celsius))

    val celsius = 0.celsius()
    println(celsius)
    println(celsius.toString(Kelvin))

    val time2 = 30.5.day() + 30.minute() + 20.second()
    println(time2)
    println(time2.toDecomposedString(Hour))


}