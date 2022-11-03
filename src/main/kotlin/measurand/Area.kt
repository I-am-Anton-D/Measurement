package measurand

import quantity.AbstractQuantity
import unit.area.AreaUnit
import java.math.BigDecimal

class Area(number: Number) : AbstractQuantity<Area>(number) {
    override val baseUnit = AreaUnit

    override fun copyWith(value: BigDecimal): AbstractQuantity<Area> {
        return Area(value)
    }
}