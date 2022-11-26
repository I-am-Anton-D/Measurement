package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.Prefix
import unit.force.Newton
import unit.length.Meter
import unit.mass.Gram
import unit.time.Second
import java.math.BigDecimal

class Force(number: Number) : AbstractQuantity<Force>(number, Newton) {

    constructor(number: Number, defaultToStringDimension: Dimension<Force>?) : this(number) {
        this.defaultToStringDimension = defaultToStringDimension
    }

    override fun copyWith(value: BigDecimal) = Force(value, defaultToStringDimension)
}

