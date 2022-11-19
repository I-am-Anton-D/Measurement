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

    override fun copyWith(value: BigDecimal): AbstractQuantity<Velocity> {
        return Velocity(value)
    }

    companion object {
        fun dimension(length: AbstractUnit<Length>, time: AbstractUnit<Time>) = (length / time) as Dimension<Velocity>

        fun kmh() = (Meter.prefix(Prefix.KILO) / Hour) as Dimension<Velocity>
        fun msec() = (Meter / Second) as Dimension<Velocity>
    }
}

fun Number.msec(): Velocity {
    return Velocity(this)
}

fun Number.kmh(): Velocity {
    //FIX IT
    val value = Velocity.msec().convertValue(Velocity.kmh(), this)
    return Velocity(value)
}