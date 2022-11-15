package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.Prefix
import unit.length.Meter
import java.math.BigDecimal
import java.math.MathContext

class Area(number: Number) : AbstractQuantity<Area>(number, Dimension(Meter, Meter)) {

    override fun copyWith(value: BigDecimal): AbstractQuantity<Area> {
        return Area(value)
    }

    operator fun div(other: Length) = Length(value.divide(other.value, MathContext.DECIMAL128))
    operator fun times(other: Length) = Volume(value * other.value)

    companion object {
        fun sqkm() =  Dimension<Area>(Meter.pow(1, prefix = Prefix.KILO), Meter.pow(1, prefix = Prefix.KILO))
    }
}

fun Number.sqmeter(): Area {
    return Area(this)
}