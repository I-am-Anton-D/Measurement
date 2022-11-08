package quantity

import dimension.Dimension
import unit.prototype.DimensionUnit
import java.text.DecimalFormat
import java.util.*

abstract class DimensionQuantity<Q>(number: Number) : AbstractQuantity<Q>(number) {
    abstract override val baseUnit: DimensionUnit<Q>

    fun toString(locale: Locale, df: DecimalFormat? = null, dimension: Dimension? = null) : String {
        val valueString = if (df == null) value.stripTrailingZeros().toPlainString() else df.format(value)
        val unitString = baseUnit.toString(locale)
        return "$valueString $unitString"
    }
}