package measurand

import dimension.Dimension

import unit.length.Meter
import java.math.BigDecimal
import java.math.MathContext

class Volume(number: Number) : AbstractQuantity<Volume>(number, Dimension(Meter, Meter, Meter)) {

    operator fun div(other: Length) = Area(value.divide(other.value, MathContext.DECIMAL128))
    operator fun div(other: Area) = Length(value.divide(other.value, MathContext.DECIMAL128))

    override fun copyWith(value: BigDecimal): AbstractQuantity<Volume> {
        return Volume(value)
    }
}