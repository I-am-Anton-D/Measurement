package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.Prefix
import unit.length.Meter
import unit.prototype.AbstractUnit
import unit.time.Hour
import unit.time.Second
import java.math.BigDecimal


class Velocity(number: Number) : AbstractQuantity<Velocity>(number, msec()) {

    constructor(number: Number, defaultToStringDimension: Dimension<Velocity>?) : this(number) {
        this.defaultToStringDimension = defaultToStringDimension
    }

    override fun copyWith(value: BigDecimal) = Velocity(value, defaultToStringDimension)


    @Suppress("UNCHECKED_CAST")
    companion object {
        fun dimension(length: AbstractUnit<Length>, time: AbstractUnit<Time>) = (length / time) as Dimension<Velocity>

        fun kmh() = (Meter.KILO / Hour) as Dimension<Velocity>
        fun msec() = (Meter / Second) as Dimension<Velocity>
    }
}

fun Number.msec(): Velocity {
    return Velocity(this)
}

fun Number.kmh(): Velocity {
    //FIX IT
    val value = Velocity.kmh().convertValue(Velocity.msec(), this)
    return Velocity(value, Velocity.kmh())
}