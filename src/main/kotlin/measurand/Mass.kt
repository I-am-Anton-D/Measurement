package measurand

import quantity.Quantity
import units.Gram
import java.math.BigDecimal

class Mass (number: Number) : Quantity<Mass>(number, Gram::class) {
    override fun copyWith(value: BigDecimal): Quantity<Mass> {
        return Mass(value)
    }
}

fun Number.gram() : Mass {
    return Mass(this)
}