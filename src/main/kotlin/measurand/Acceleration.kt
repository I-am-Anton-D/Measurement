package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.time.Second
import java.math.BigDecimal

class Acceleration(number: Number) : AbstractQuantity<Acceleration>(number, baseDimension()) {

    constructor(number: Number, defaultToStringDimension: Dimension<Acceleration>) : this(number) {
        this.defaultToStringDimension = defaultToStringDimension
    }

    override fun copyWith(value: BigDecimal) = Acceleration(value, defaultToStringDimension)

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun baseDimension() = (Velocity.msec() / Second) as Dimension<Acceleration>
    }
}


