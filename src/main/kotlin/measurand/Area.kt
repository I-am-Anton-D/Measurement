package measurand

import dimension.Dimension
import unit.length.Meter
import java.math.BigDecimal
import java.math.MathContext

class Area(number: Number) : AbstractQuantity<Area>(number, sqm()) {

    override fun copyWith(value: BigDecimal): AbstractQuantity<Area> {
        return Area(value)
    }

    operator fun div(other: Length) = Length(value.divide(other.value, MathContext.DECIMAL128))
    operator fun times(other: Length) = Volume(value * other.value)

    companion object {
        fun sqkm() =  (Meter.KILO * Meter.KILO) as Dimension<Area>
        fun sqm() = (Meter * Meter) as Dimension<Area>
    }
}

fun Number.sqmeter(): Area {
    return Area(this)
}