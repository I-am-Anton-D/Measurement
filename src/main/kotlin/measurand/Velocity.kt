package measurand

import quantity.AbstractQuantity
import quantity.DimensionQuantity
import unit.Speed
import java.math.BigDecimal

class Velocity(number: Number) : DimensionQuantity<Velocity>(number) {
    override val baseUnit = Speed

    override fun copyWith(value: BigDecimal): AbstractQuantity<Velocity> {
        return Velocity(value)
    }
}