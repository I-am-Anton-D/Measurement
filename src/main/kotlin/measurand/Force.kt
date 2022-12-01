package measurand

import dimension.Dimension
import unit.force.Newton
import java.math.BigDecimal

class Force(number: Number) : AbstractQuantity<Force>(number, Newton) {

    constructor(number: Number, defaultToStringDimension: Dimension<Force>) : this(number) {
        this.toStringDimension = defaultToStringDimension
    }

    constructor(mass:Mass, acceleration: Acceleration) : this(mass.value * acceleration.value)

    override fun copyWith(value: BigDecimal) = Force(value, toStringDimension)
}

