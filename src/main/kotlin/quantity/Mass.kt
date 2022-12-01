package quantity

import dimension.Dimension
import dimension.Prefix
import unit.mass.Gram
import java.math.BigDecimal

class Mass(number: Number) : AbstractQuantity<Mass>(number, kg()) {

    constructor(number: Number, toStringDimension: Dimension<Mass>) : this(number) {
        this.toStringDimension = toStringDimension
    }

    operator fun times(acceleration: Acceleration) = Force(this, acceleration)

    override fun copyWith(value: BigDecimal) = Mass(value, this.toStringDimension)

    companion object {
        fun kg() = Dimension<Mass>(Gram.prefix(Prefix.KILO))
    }
}

fun Number.massFrom(dimension: Dimension<Mass>) = Mass(dimension.convertValue(Mass.kg(),this), dimension)

fun Number.kg() = Mass(this)