package measurand

import quantity.MetricQuantity
import units.Gram
import java.math.BigDecimal

class Mass (number: Number) : MetricQuantity<Mass>(number, Gram::class) {
    override fun copyWith(value: BigDecimal): Mass {
        return Mass(value)
    }
}

fun Number.gram() : Mass {
    return Mass(this)
}