package extension

import dimension.Dimension
import quantity.AbstractQuantity
import java.text.DecimalFormat
import java.util.*

fun <Q> AbstractQuantity<Q>.toAnsiString(
    dimension: Dimension<Q>? = null,
    valueFormat: DecimalFormat? = null,
    locale: Locale = Locale("en", "GB")
): String {
    val targetDimension = dimension ?: toStringDimension
    val valueIn = valueIn(targetDimension)
    val valueString = valueFormat?.format(valueIn) ?: valueIn.stripTrailingZeros().toPlainString()
    val unitString = targetDimension.toAnsiString(locale)

    return "$valueString $unitString"
}