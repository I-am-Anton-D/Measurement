package quantity

import dimension.Dimension
import java.math.BigDecimal

class Quantity(number: Number, override val dimension: Dimension) : AbstractQuantity<Quantity>(number) {

    override fun copyWith(value: BigDecimal): AbstractQuantity<Quantity> {
        return Quantity(value, dimension)
    }
}