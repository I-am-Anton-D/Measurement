package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.force.Newton
import java.math.BigDecimal

class Force(number: Number) : AbstractQuantity<Force>(number, Newton) {

    constructor(number: Number, defaultToStringDimension: Dimension<Force>) : this(number) {
        this.toStringDimension = defaultToStringDimension
    }

    override fun copyWith(value: BigDecimal) = Force(value, toStringDimension)
}

