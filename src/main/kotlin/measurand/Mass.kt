package measurand

import quantity.AbstractQuantity
import quantity.QuantityFactory
import java.math.BigDecimal

class Mass(number: Number) : AbstractQuantity<Mass>(number) {
    override val dimension = QuantityFactory.dimensionFor(this::class)
    override fun copyWith(value: BigDecimal) = Mass(value)
}

fun Number.gram() : Mass {
    return Mass(this)
}