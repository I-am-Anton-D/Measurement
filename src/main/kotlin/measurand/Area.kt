package measurand

import dimension.Dimension
import dimension.UnitHolder
import quantity.AbstractQuantity
import quantity.DimensionQuantity
import unit.Prefix
import unit.area.AreaUnit
import unit.length.Meter
import java.math.BigDecimal
import java.math.MathContext

class Area(number: Number) : DimensionQuantity<Area>(number) {
    override val baseUnit = AreaUnit

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