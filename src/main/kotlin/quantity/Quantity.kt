package quantity

import dimension.Dimension
import java.math.BigDecimal

class Quantity(number: Number, dimension: Dimension<Quantity>) : AbstractQuantity<Quantity>(number, dimension) {

    override fun copyWith(value: BigDecimal): AbstractQuantity<Quantity> {
        return Quantity(value, this.dimension)
    }
}