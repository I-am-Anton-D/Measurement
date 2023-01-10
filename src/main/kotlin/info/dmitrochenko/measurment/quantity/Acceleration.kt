package info.dmitrochenko.measurment.quantity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.unit.time.Second
import java.math.BigDecimal
import java.math.MathContext

class Acceleration(number: Number) : AbstractQuantity<info.dmitrochenko.measurment.quantity.Acceleration>(number,
    info.dmitrochenko.measurment.quantity.Acceleration.Companion.baseDimension()
) {

    constructor(number: Number, toStringDimension: Dimension<info.dmitrochenko.measurment.quantity.Acceleration>) : this(number) {
        this.toStringDimension = toStringDimension
    }

    constructor(velocity: info.dmitrochenko.measurment.quantity.Velocity, time: info.dmitrochenko.measurment.quantity.Time) : this(
        velocity.value.divide(time.value, MathContext.DECIMAL128),
        Dimension<info.dmitrochenko.measurment.quantity.Acceleration>(velocity.toStringDimension / time.toStringDimension)
    )

    override fun copyWith(value: BigDecimal) = info.dmitrochenko.measurment.quantity.Acceleration(value, toStringDimension)

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun baseDimension() = (info.dmitrochenko.measurment.quantity.Velocity.Companion.msec() / Second) as Dimension<info.dmitrochenko.measurment.quantity.Acceleration>
    }
}


