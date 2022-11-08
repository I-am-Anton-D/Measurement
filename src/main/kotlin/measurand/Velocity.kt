package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import quantity.DimensionQuantity
import unit.length.Meter
import unit.time.Hour
import unit.velocity.Speed
import java.math.BigDecimal

class Velocity(number: Number) : DimensionQuantity<Velocity>(number) {
    override val baseUnit = Speed

    override fun copyWith(value: BigDecimal): AbstractQuantity<Velocity> {
        return Velocity(value)
    }
}

fun Number.msec() : Velocity {
    return Velocity(this)
}

fun Number.kmh() : Velocity {
    val value = Dimension.convertValue(Dimension(Meter.pow(1), Hour.pow(-1)), Speed.dimension, this)
    return Velocity(value)
}