package measurand

import dimension.Dimension
import unit.time.Second
import java.math.BigDecimal
import java.math.MathContext

class Acceleration(number: Number) : AbstractQuantity<Acceleration>(number, baseDimension()) {

    constructor(number: Number, toStringDimension: Dimension<Acceleration>) : this(number) {
        this.toStringDimension = toStringDimension
    }

    constructor(velocity: Velocity, time: Time) : this(
        velocity.value.divide(time.value, MathContext.DECIMAL128),
        Dimension<Acceleration>(velocity.toStringDimension / time.toStringDimension)
    )

    override fun copyWith(value: BigDecimal) = Acceleration(value, toStringDimension)

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun baseDimension() = (Velocity.msec() / Second) as Dimension<Acceleration>
    }
}


