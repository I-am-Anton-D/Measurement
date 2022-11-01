package measurand

import quantity.AbstractQuantity
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import unit.prototype.Prefix
import unit.time.Day
import unit.time.Hour
import unit.time.Minute
import unit.time.Second
import java.math.BigDecimal

class Time(number: Number, useUnit: AbstractUnit<Time>? = null, usePrefix: Prefix? = null) :
    AbstractQuantity<Time>(number, Second, useUnit, usePrefix) {

    override fun copyWith(value: BigDecimal, useUnit: AbstractUnit<Time>?, usePrefix: Prefix?) =
        Time(value, useUnit, usePrefix)
}

fun Number.second(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Time> = Second): Time {
    val number = unit.valueInBaseUnit(prefix.getNominalValue(this))
    return Time(number = number, useUnit = unit, usePrefix = prefix)
}

fun Number.second(unit: AbstractUnit<Time>): Time {
    val number = unit.valueInBaseUnit(this)
    return Time(number = number, useUnit = unit)
}

fun Number.minute() = second(Minute)
fun Number.hour() = second(Hour)
fun Number.day() = second(Day)