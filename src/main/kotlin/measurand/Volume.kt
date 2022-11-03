package measurand

import quantity.AbstractQuantity
import quantity.DimensionQuantity
import unit.VolumeUnit
import java.math.BigDecimal

class Volume(number: Number) : DimensionQuantity<Volume>(number) {
    override val baseUnit = VolumeUnit

    operator fun div(other: Length) = Area(value / other.value)
    operator fun div(other: Area) = Length(value / other.value)

    override fun copyWith(value: BigDecimal): AbstractQuantity<Volume> {
        return Volume(value)
    }
}