package measurand

import quantity.AbstractQuantity
import quantity.DimensionQuantity
import unit.volume.VolumeUnit
import java.math.BigDecimal
import java.math.MathContext

class Volume(number: Number) : DimensionQuantity<Volume>(number) {
    override val baseUnit = VolumeUnit

    operator fun div(other: Length) = Area(value.divide(other.value, MathContext.DECIMAL128))
    operator fun div(other: Area) = Length(value.divide(other.value, MathContext.DECIMAL128))

    override fun copyWith(value: BigDecimal): AbstractQuantity<Volume> {
        return Volume(value)
    }
}