package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.Prefix
import unit.length.Meter
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import java.math.BigDecimal

class Length(number: Number) : AbstractQuantity<Length>(number, Meter) {

    constructor(number: Number, defaultToStringDimension: Dimension<Length>) : this(number) {
        this.defaultToStringDimension = defaultToStringDimension
    }

    operator fun times(other: Length) = Area(value * other.value)
    operator fun div(time: Time) = Velocity(this, time)

    override fun copyWith(value: BigDecimal) = Length(value, defaultToStringDimension)
}

fun Number.meter(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Length> = Meter) =
    Length(unit.valueToBaseUnit(this, prefix), unit.prefix(prefix))

fun Number.meter(unit: AbstractUnit<Length>) = Length(unit.valueToBaseUnit(this), Dimension(unit))

fun Number.km() = meter(Prefix.KILO)
fun Number.cm() = meter(Prefix.CENTI)
fun Number.mm() = meter(Prefix.MILLI)