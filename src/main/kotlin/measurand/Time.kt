package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.Prefix
import unit.prototype.AbstractUnit
import unit.time.Day
import unit.time.Hour
import unit.time.Minute
import unit.time.Second
import java.math.BigDecimal

class Time(number: Number, from: Dimension<Time> = Second.toDimension()) : AbstractQuantity<Time>(number, Second) {
    override val dimension = Second.toDimension()

    constructor(number: Number, unit: AbstractUnit<Time>) : this(number, unit.toDimension())

    override fun copyWith(value: BigDecimal) = Time(value, defaultToStringDimension)
}

fun Number.second(prefix: Prefix = Prefix.NOMINAL) = Time(this, Second.prefix(prefix))

fun Number.minute() = Time(this, Minute)
fun Number.hour() = Time(this, Hour)
fun Number.day() = Time(this, Day)