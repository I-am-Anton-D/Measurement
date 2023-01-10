package info.dmitrochenko.measurment.quantity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.unit.mass.Gram
import java.math.BigDecimal

class Mass(number: Number) : AbstractQuantity<Mass>(number, kg()) {

    constructor(number: Number, toStringDimension: Dimension<Mass>) : this(number) {
        this.toStringDimension = toStringDimension
    }

    operator fun times(acceleration: info.dmitrochenko.measurment.quantity.Acceleration) = Force(this, acceleration)

    override fun copyWith(value: BigDecimal) = Mass(value, this.toStringDimension)

    companion object {
        fun kg() = Dimension<Mass>(Gram.prefix(Prefix.KILO))
    }
}

fun Number.massFrom(dimension: Dimension<Mass>) = Mass(dimension.convertValue(this, Mass.kg()), dimension)

fun Number.kg() = Mass(this)