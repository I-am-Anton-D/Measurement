package measurand

import quantity.AbstractQuantity
import quantity.QuantityFactory
import java.math.BigDecimal

class Volume(number: Number) : AbstractQuantity<Volume>(number) {
    override val dimension = QuantityFactory.dimensionFor(this::class)

    override fun copyWith(value: BigDecimal): AbstractQuantity<Volume> {
        return Volume(value)
    }
}