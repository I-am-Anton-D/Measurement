package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import unit.length.Meter
import java.math.BigDecimal
import java.math.MathContext

class Area(number: Number, from: Dimension<Area> = sqm()) : AbstractQuantity<Area>(number, from) {
    override val dimension = sqm()

    override fun copyWith(value: BigDecimal): AbstractQuantity<Area> {
        return Area(value, defaultToStringDimension)
    }

    operator fun div(other: Length) = Length(value.divide(other.value, MathContext.DECIMAL128))
    //   operator fun times(other: Length) = Volume(value * other.value)

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun sqkm() = (Meter.KILO * Meter.KILO) as Dimension<Area>
        fun sqm() = (Meter * Meter) as Dimension<Area>
    }
}

fun Number.sqm(): Area {
    return Area(this, Area.sqm())
}

fun Number.sqkm(): Area {
    return Area(this, Area.sqkm())
}