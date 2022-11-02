package measurand

import quantity.AbstractQuantity
import quantity.ToStringParameters
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import unit.prototype.Prefix
import unit.time.Day
import unit.time.Hour
import unit.time.Minute
import unit.time.Second
import java.math.BigDecimal

class Time(number: Number) : AbstractQuantity<Time>(number) {
    override val baseUnit = Second

    constructor(number: Number, toStringParameters: ToStringParameters<Time>) : this(number) {
        this.defaultToStringParameters = toStringParameters
    }

    override fun copyWith(value: BigDecimal) = Time(value, defaultToStringParameters)
}

fun Number.second(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Time> = Second): Time {
    val number = unit.valueInBaseUnit(prefix.getNominalValue(this))
    return Time(number, ToStringParameters(unit = unit, prefix = prefix))
}

fun Number.second(unit: AbstractUnit<Time>): Time {
    val number = unit.valueInBaseUnit(this)
    return Time(number, ToStringParameters(unit = unit))
}

fun Number.minute() = second(Minute)
fun Number.hour() = second(Hour)
fun Number.day() = second(Day)