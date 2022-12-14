package info.dmitrochenko.measurment.quantity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.dimension.Prefix.*
import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.unit.length.Meter

import java.math.BigDecimal

class Length(number: Number) : AbstractQuantity<Length>(number, Meter) {

    constructor(number: Number, toStringDimension: Dimension<Length>) : this(number) {
        this.toStringDimension = toStringDimension
    }

    operator fun times(other: Length) = Area(value * other.value)
    operator fun div(time: Time) = Velocity(this, time)

    override fun copyWith(value: BigDecimal) = Length(value, toStringDimension)
}

fun Number.meter(prefix: Prefix = NOMINAL) = Length(prefix.inNominal(this), Meter.prefix(prefix))
fun Number.lengthIn(dimension: Dimension<Length>) = Length(dimension.convertValue(this, Meter), dimension)

fun Number.km() = meter(KILO)
fun Number.cm() = meter(CENTI)
fun Number.mm() = meter(MILLI)