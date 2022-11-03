package quantity

import unit.prototype.DimensionUnit
import java.math.BigDecimal

class Quantity(number: Number, override val baseUnit: DimensionUnit<Quantity>) : DimensionQuantity<Quantity>(number) {

    override fun copyWith(value: BigDecimal): AbstractQuantity<Quantity> {
        return Quantity(value, baseUnit)
    }
}