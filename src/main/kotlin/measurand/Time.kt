package measurand

import quantity.AbstractQuantity
import unit.Prefix
import quantity.ToStringParameters
import unit.prototype.AbstractUnit
import unit.time.Day
import unit.time.Hour
import unit.time.Minute
import unit.time.Second
import java.math.BigDecimal

class Time(number: Number) : AbstractQuantity<Time>(number, Second) {

    constructor(number: Number, toStringParameters: ToStringParameters<Time>) : this(number) {
       // this.defaultToStringParameters = toStringParameters
    }

    override fun copyWith(value: BigDecimal) = Time(value)
}

fun Number.second(prefix: Prefix = Prefix.NOMINAL, unit: AbstractUnit<Time> = Second): Time {
    val number = prefix.inNominal(this).multiply(unit.ratio)
    return Time(number, ToStringParameters(unit, prefix))
}

fun Number.second(unit: AbstractUnit<Time>): Time {
    val number = BigDecimal(this.toString()).multiply(unit.ratio)
    return Time(number, ToStringParameters(unit))
}

fun Number.minute() = second(Minute)
fun Number.hour() = second(Hour)
fun Number.day() = second(Day)