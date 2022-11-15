package measurand


import quantity.AbstractQuantity
import unit.Prefix
import quantity.ToStringParameters
import unit.length.Foot
import unit.length.Inch
import unit.length.Meter
import unit.length.Mile
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import java.math.BigDecimal
import java.math.MathContext

class Length(number: Number) : AbstractQuantity<Length>(number, Meter) {

    constructor(number: Number, toStringParameters: ToStringParameters<Length>) : this(number) {
        //this.defaultToStringParameters = toStringParameters
    }

    operator fun times(other: Length) = Area(value * other.value)
    operator fun div(other: Time) = Velocity(value.divide(other.value, MathContext.DECIMAL128))

    override fun copyWith(value: BigDecimal) = Length(value)
}

fun Number.meter(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Length> = Meter): Length {
    val number = prefix.inNominal(this).multiply(unit.ratio)
    return Length(number, ToStringParameters(unit, prefix))
}

fun Number.meter(unit: AbstractUnit<Length>): Length {
    val number = BigDecimal(this.toString()).multiply(unit.ratio)
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