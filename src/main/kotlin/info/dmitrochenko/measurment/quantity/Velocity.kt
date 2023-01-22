package info.dmitrochenko.measurment.quantity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.unit.length.Meter
import info.dmitrochenko.measurment.unit.length.Mile
import info.dmitrochenko.measurment.unit.time.Hour
import info.dmitrochenko.measurment.unit.time.Second
import java.math.BigDecimal
import java.math.MathContext


class Velocity(number: Number) : AbstractQuantity<Velocity>(number, msec()) {

    constructor(number: Number, defaultToStringDimension: Dimension<Velocity>) : this(number) {
        this.toStringDimension = defaultToStringDimension
    }

    constructor(length: Length, time: Time) : this(
        length.value.divide(time.value, MathContext.DECIMAL128),
        Dimension<Velocity>(length.toStringDimension / time.toStringDimension)
    )

    operator fun div(time: Time) = Acceleration(this, time)

    override fun copyWith(value: BigDecimal) = Velocity(value, toStringDimension)

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun kmh() = (Meter.KILO / Hour) as Dimension<Velocity>
        fun msec() = (Meter / Second) as Dimension<Velocity>
        fun mph() = (Mile / Hour) as Dimension<Velocity>
    }
}

fun Number.velocityFrom(dimension: Dimension<Velocity>) = Velocity(dimension.convertValue(this, Velocity.msec()), dimension)

fun Number.msec() = Velocity(this)
fun Number.kmh() = velocityFrom(Velocity.kmh())
fun Number.mph() = velocityFrom(Velocity.mph())