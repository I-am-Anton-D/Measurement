package measurand

import quantity.AbstractQuantity
import unit.mass.Gram
import java.math.BigDecimal

class Mass(number: Number) : AbstractQuantity<Mass>(number, Gram) {
    override fun copyWith(value: BigDecimal) = Mass(value)
}

fun Number.gram() : Mass {
    return Mass(this)
}