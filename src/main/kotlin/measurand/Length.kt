package measurand

import unit.Prefix
import quantity.BaseQuantity
import quantity.ToStringParameters
import unit.length.Foot
import unit.length.Inch
import unit.length.Meter
import unit.length.Mile
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import java.math.BigDecimal

class Length(number: Number) : BaseQuantity<Length>(number) {
    override val baseUnit = Meter

    constructor(number: Number, toStringParameters: ToStringParameters<Length>) : this(number) {
        this.defaultToStringParameters = toStringParameters
    }

    operator fun times(other: Length) = Area(value * other.value)
    operator fun div(other: Time) = Velocity(value / other.value)

    override fun copyWith(value: BigDecimal) = Length(value, defaultToStringParameters)
}

fun Number.meter(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Length> = Meter): Length {
    val number = unit.valueInBaseUnit(prefix.getNominalValue(this))
    return Length(number, ToStringParameters(unit, prefix))
}

fun Number.meter(unit: AbstractUnit<Length>): Length {
    val number = unit.valueInBaseUnit(this)
    return Length(number, ToStringParameters(unit))
}

fun Number.km() = meter(Prefix.KILO)
fun Number.cm() = meter(Prefix.CENTI)
fun Number.mm() = meter(Prefix.MILLI)

fun Number.inch() = meter(Inch)
fun Number.foot() = meter(Foot)
fun Number.mile() = meter(Mile)

fun Length.toMile() = valueIn(Mile)
fun Length.toInch() = valueIn(Inch)
fun Length.toFoot() = valueIn(Foot)