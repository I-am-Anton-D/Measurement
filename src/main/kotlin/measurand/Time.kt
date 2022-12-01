package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.Prefix
import unit.Prefix.NOMINAL
import unit.prototype.AbstractUnit
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

fun Number.second(prefix: Prefix = NOMINAL) = Time(prefix.inNominal(this), Second.prefix(prefix))
fun Number.timeIn(dimension:Dimension<Time>) = Time(dimension.convertValue(Second, this), dimension)
fun Number.timeIn(unit:AbstractUnit<Time>) = timeIn(unit.toDimension())

fun Number.minute() = timeIn(Minute)
fun Number.hour() = timeIn(Hour)
fun Number.day() = timeIn(Day)