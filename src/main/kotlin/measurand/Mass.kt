package measurand

import quantity.AbstractQuantity
import unit.mass.Gram
import unit.prototype.AbstractUnit
import unit.prototype.Prefix
import java.math.BigDecimal

class Mass(number: Number) : AbstractQuantity<Mass>(number, Gram) {
    override fun copyWith(value: BigDecimal, useUnit: AbstractUnit<Mass>?, usePrefix: Prefix?) = Mass(value)
}

fun Number.gram() : Mass {
    return Mass(this)
}