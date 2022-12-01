import measurand.*
import unit.length.inch
import unit.length.mile
import unit.mass.gram
import unit.mass.ng


fun main() {
    val mass = 10.kg()
    println(mass)

    val length = 10.km()
    println(length)

    val time = 1.second()
    println(time)

    val velocity = length / time
    println(velocity)

    val acceleration = velocity / 1.second()
    println(acceleration)

    val force = mass * acceleration
    println(force)
}