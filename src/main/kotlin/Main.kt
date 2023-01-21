import info.dmitrochenko.measurment.extension.toAnsiString
import info.dmitrochenko.measurment.extension.toDecomposedString
import info.dmitrochenko.measurment.extension.toFractionalString
import info.dmitrochenko.measurment.quantity.*
import info.dmitrochenko.measurment.unit.length.*
import info.dmitrochenko.measurment.unit.temperature.Celsius
import info.dmitrochenko.measurment.unit.temperature.Kelvin
import info.dmitrochenko.measurment.unit.temperature.celsius
import info.dmitrochenko.measurment.unit.temperature.fahrenheit
import info.dmitrochenko.measurment.unit.time.Hour
import java.util.*


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

    val kelvin = 1000.kelvin()
    println(kelvin)
    println(kelvin.toString(Celsius))

    val celsius = 0.celsius()
    println(celsius)
    println(celsius.toString(Kelvin))

    val time2 = 30.5.day() + 30.minute() + 20.second()
    println(time2)
    println(time2.toDecomposedString(Hour))

    val length2 = 10.mile()
    println(length2)
    println(length2.toString(Meter))

    val foot = 1.5.foot() + 0.5.inch()
    println(foot.toString(Inch))
    println(foot.toDecomposedString(withFraction = true))

    val inch = 5.inch() + Inch.ThreeEighth
    println(inch)
    println(inch.toFractionalString())

    val t = 100.celsius()
    println(t.toString(locale = Locale("ru", "RU")))
}