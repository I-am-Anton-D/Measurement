package info.dmitrochenko.measurment.quantity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.unit.force.Newton
import java.math.BigDecimal

class Force(number: Number) : AbstractQuantity<Force>(number, Newton) {

    constructor(number: Number, defaultToStringDimension: Dimension<Force>) : this(number) {
        this.toStringDimension = defaultToStringDimension
    }

    constructor(mass:Mass, acceleration: info.dmitrochenko.measurment.quantity.Acceleration) : this(mass.value * acceleration.value)

    override fun copyWith(value: BigDecimal) = Force(value, toStringDimension)
}

