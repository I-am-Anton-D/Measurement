package measurand

import quantity.AbstractQuantity
import units.Gram
import java.math.BigDecimal

class Mass(number: Number) : AbstractQuantity<Mass>(number, Gram) {
    override fun copyWith(value: BigDecimal): Mass {
        return Mass(value)
    }
}

fun Number.gram() : Mass {
    return Mass(this)
}