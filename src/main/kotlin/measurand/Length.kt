package measurand

import quantity.AbstractQuantity
import unit.length.Foot
import unit.length.Inch
import unit.length.Meter
import unit.length.Mile
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import unit.prototype.Prefix
import java.math.BigDecimal

class Length(number: Number, useUnits: AbstractUnit<Length>? = null, usePrefix: Prefix? = null) :
    AbstractQuantity<Length>(number, Meter, useUnits, usePrefix) {

    override fun copyWith(value: BigDecimal, useUnit: AbstractUnit<Length>?, usePrefix: Prefix?) =
        Length(value, useUnit, usePrefix)
}

fun Length.toMile() = valueIn(Mile)
fun Length.toInch() = valueIn(Inch)

fun Number.meter(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Length> = Meter): Length {
    val number = unit.valueInBaseUnit(prefix.getNominalValue(this))
    return Length(number = number, usePrefix = prefix, useUnits = unit)
}

fun Number.meter(unit: AbstractUnit<Length>): Length {
    val number = unit.valueInBaseUnit(this)
    return Length(number = number, useUnits = unit)
}

fun Number.km() = meter(Prefix.KILO)
fun Number.cm() = meter(Prefix.CENTI)
fun Number.mm() = meter(Prefix.MILLI)

fun Number.inch() = meter(Inch)
fun Number.foot() = meter(Foot)
fun Number.mile() = meter(Mile)