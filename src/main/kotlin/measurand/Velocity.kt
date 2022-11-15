package measurand

import dimension.Dimension
import dimension.UnitHolder
import quantity.AbstractQuantity
import unit.Prefix
import unit.length.Meter
import unit.prototype.AbstractUnit
import unit.time.Hour
import unit.time.Second
import unit.velocity.Speed
import java.math.BigDecimal

class Velocity(number: Number) : AbstractQuantity<Velocity>(number, msec()) {

    override fun copyWith(value: BigDecimal): AbstractQuantity<Velocity> {
        return Velocity(value)
    }

    companion object {
        fun dimension(length: AbstractUnit<Length>, time: AbstractUnit<Time>) =
            Dimension<Velocity>(UnitHolder(length), UnitHolder(time, -1))

        fun kmh() = Dimension<Velocity>(Meter.pow(1, Prefix.KILO), Hour.pow(-1))
        fun msec() = Dimension<Velocity>(Meter.pow(1), Second.pow(-1))
    }
}

fun Number.msec(): Velocity {
    return Velocity(this)
}

fun Number.kmh(): Velocity {
    val value = Velocity.msec().convertValue(Velocity.kmh(), this)
    return Velocity(value)
}