package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.Prefix
import unit.length.Meter
import unit.prototype.AbstractUnit
import java.math.BigDecimal

class Length(number: Number, from: Dimension<Length> = Meter.toDimension()) : AbstractQuantity<Length>(number, from) {

    constructor(number: Number, unit: AbstractUnit<Length>) : this(number, unit.toDimension())

    operator fun times(other: Length) = Area(value * other.value)
    operator fun div(time: Time) = Velocity(this, time)

    override fun copyWith(value: BigDecimal) = Length(value, defaultToStringDimension)

    companion object {
        val dimension = Meter.toDimension()
    }
}


fun Number.meter(prefix: Prefix = Prefix.NOMINAL) = Length(this, Meter.prefix(prefix))

fun Number.km() = meter(Prefix.KILO)
fun Number.cm() = meter(Prefix.CENTI)
fun Number.mm() = meter(Prefix.MILLI)