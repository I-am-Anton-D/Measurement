package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.length.Meter
import unit.time.Hour
import unit.time.Second
import java.math.BigDecimal


class Velocity(number: Number, from: Dimension<Velocity> = msec()) : AbstractQuantity<Velocity>(number, from) {
    override val dimension = msec()

    constructor(length: Length, time: Time) : this(length.value / time.value) {
        defaultToStringDimension = dimension(length, time)
    }

    // operator fun div(other: Time) = Acceleration(value.divide(other.value, MathContext.DECIMAL128))

    override fun copyWith(value: BigDecimal) = Velocity(value, defaultToStringDimension)

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun dimension(length: Length, time: Time) = (length.dimension / time.dimension) as Dimension<Velocity>


        fun kmh() = (Meter.KILO / Hour) as Dimension<Velocity>
        fun msec() = (Meter / Second) as Dimension<Velocity>
    }
}

fun Number.msec() = Velocity(this)
fun Number.kmh() = Velocity(this, Velocity.kmh())