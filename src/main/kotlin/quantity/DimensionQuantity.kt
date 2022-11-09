package quantity

import dimension.Dimension
import unit.prototype.DimensionUnit
import java.text.DecimalFormat
import java.util.*

abstract class DimensionQuantity<Q>(number: Number) : AbstractQuantity<Q>(number) {
    abstract override val baseUnit: DimensionUnit<Q>

    open fun valueIn(dimension: Dimension) =
        baseUnit.convertValueToDimension(dimension, value)

    open fun toString(
        locale: Locale = Locale.getDefault(),
        df: DecimalFormat? = null,
        dimension: Dimension? = null
    ): String {
        val valueIn = if (dimension == null) value else valueIn(dimension)
        val valueString = df?.format(valueIn) ?: valueIn.stripTrailingZeros().toPlainString()
        val unitString = dimension?.toString(locale) ?: baseUnit.toString(locale)
        return "$valueString $unitString"
    }
}