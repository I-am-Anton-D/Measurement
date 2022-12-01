package quantity

import dimension.Dimension
import unit.prototype.DimensionUnit
import java.text.DecimalFormat
import java.util.*

//abstract class DimensionQuantity<Q>(number: Number) : AbstractQuantity<Q>(number) {
//    abstract override val dimension: DimensionUnit<Q>
//
//    open fun valueIn(dimension: Dimension<Q>) =
//        this.dimension.convertValueToDimension(dimension, value)
//
//    open fun toString(
//        dimension: Dimension<Q>? = null,
//        df: DecimalFormat? = null,
//        locale: Locale = Locale.getDefault()
//    ): String {
//        val valueIn = if (dimension == null) value else valueIn(dimension)
//        val valueString = df?.format(valueIn) ?: valueIn.stripTrailingZeros().toPlainString()
//        val unitString = dimension?.toString(locale) ?: this.dimension.toString(locale)
//        return "$valueString $unitString"
//    }
//}