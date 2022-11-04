package measurand

import quantity.BaseQuantity
import unit.mass.Gram
import java.math.BigDecimal

class Mass(number: Number) : BaseQuantity<Mass>(number) {
    override val baseUnit = Gram
    override fun copyWith(value: BigDecimal) = Mass(value)
}

fun Number.gram() : Mass {
    return Mass(this)
}