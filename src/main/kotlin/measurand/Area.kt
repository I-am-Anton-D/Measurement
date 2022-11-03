package measurand

import quantity.AbstractQuantity
import quantity.DimensionQuantity
import unit.area.Square
import java.math.BigDecimal

class Area(number: Number) : DimensionQuantity<Area>(number) {
    override val baseUnit = Square

    override fun copyWith(value: BigDecimal): AbstractQuantity<Area> {
        return Area(value)
    }

    operator fun div(other: Length) = Length(value / other.value)
    operator fun times(other: Length) = Volume(value * other.value)
}

fun Number.sqmeter(): Area {
    return Area(this)
}