package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import quantity.DimensionQuantity
import unit.Prefix
import unit.length.Meter
import unit.time.Hour
import unit.velocity.Speed
import java.math.BigDecimal

class Velocity(number: Number) : DimensionQuantity<Velocity>(number) {
    override val baseUnit = Speed

    override fun copyWith(value: BigDecimal): AbstractQuantity<Velocity> {
        return Velocity(value)
    }

    companion object {
        fun kmh() = Dimension(Meter.pow(1, Prefix.KILO), Hour.pow(-1))
    }
}

fun Number.msec() : Velocity {
    return Velocity(this)
}

fun Number.kmh() : Velocity {
    val value = Speed.convertValueToDimension(Dimension(Meter.pow(1), Hour.pow(-1)), this)
    return Velocity(value)
}