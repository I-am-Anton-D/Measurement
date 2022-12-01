package measurand

import dimension.Dimension
import unit.length.Meter
import unit.length.Mile
import unit.time.Hour
import unit.time.Second
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

fun Number.velocityFrom(dimension: Dimension<Velocity>) = Velocity(dimension.convertValue(Velocity.msec(), this))

fun Number.msec() = Velocity(this)
fun Number.kmh() = velocityFrom(Velocity.kmh())
fun Number.mph() = velocityFrom(Velocity.mph())