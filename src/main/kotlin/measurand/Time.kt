package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.Prefix

import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import unit.time.Day
import unit.time.Hour
import unit.time.Minute
import unit.time.Second
import java.math.BigDecimal

class Time(number: Number) : AbstractQuantity<Time>(number, Second) {

    constructor(number: Number, defaultToStringDimension: Dimension<Time>) : this(number) {
        this.toStringDimension = defaultToStringDimension
    }

    override fun copyWith(value: BigDecimal) = Time(value, toStringDimension)
}

fun Number.second(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Time> = Second) =
    Time(unit.valueToBaseUnit(this, prefix), Dimension(unit, 1, prefix))


fun Number.second(unit: AbstractUnit<Time>) = Time(unit.valueToBaseUnit(this), Dimension(unit))


fun Number.minute() = second(Minute)
fun Number.hour() = second(Hour)
fun Number.day() = second(Day)