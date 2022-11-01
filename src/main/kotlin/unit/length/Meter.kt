package unit.length

import measurand.Length
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import unit.prototype.Prefix

object Meter : MetricUnit<Length>()

fun Number.meter(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Length> = Meter): Length {
    val number = unit.valueInBaseUnit(prefix.getNominalValue(this))
    return Length(number = number, usePrefix = prefix, useUnits = unit)
}

fun Number.meter(unit: AbstractUnit<Length>): Length {
    val number = unit.valueInBaseUnit(this)
    return Length(number = number, useUnits = unit)
}

fun Number.kilometer() = meter(Prefix.KILO)
fun Number.km() = kilometer()
fun Number.centimeter() = meter(Prefix.CENTI)
fun Number.cm() = centimeter()
fun Number.millimeter() = meter(Prefix.MILLI)
fun Number.mm() = millimeter()