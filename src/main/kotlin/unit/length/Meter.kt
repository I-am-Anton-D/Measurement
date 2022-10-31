package unit.length

import measurand.Length
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import util.Prefix

object Meter : MetricUnit<Length>()

fun Number.meter(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Length> = Meter) =
    Length(unit.valueInBaseUnit(prefix.getNominalValue(this)))

fun Number.meter(unit: AbstractUnit<Length>) =
    Length(unit.valueInBaseUnit(this))

fun Number.kilometer() = meter(Prefix.KILO)
fun Number.km() = kilometer()
fun Number.centimeter() = meter(Prefix.CENTI)
fun Number.cm() = centimeter()
fun Number.millimeter() = meter(Prefix.MILLI)
fun Number.mm() = millimeter()