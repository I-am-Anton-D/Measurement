package extension

import dimension.Dimension
import exception.DecompositionException
import quantity.AbstractQuantity
import unit.abstract.CompositeUnit
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.util.*

fun <Q> AbstractQuantity<Q>.toAnsiString(
    dimension: Dimension<Q>? = null,
    valueFormat: DecimalFormat? = null,
    locale: Locale = Locale("en", "GB")
): String {
    val targetDimension = dimension ?: toStringDimension
    val valueIn = valueIn(targetDimension)
    val valueString = valueFormat?.format(valueIn) ?: valueIn.out()
    val unitString = targetDimension.toAnsiString(locale)

    return "$valueString $unitString"
}

fun <Q> AbstractQuantity<Q>.toDecomposedString(decomposeFrom:CompositeUnit<Q>? = null, locale: Locale = Locale.getDefault()) : String {
    val targetDimension = decomposeFrom?.toDimension() ?: toStringDimension
    if (!targetDimension.isSingleUnit()) throw DecompositionException("Size of dimension of quantity not equal 1")

    val unit = targetDimension.getSingleUnit()
    if (targetDimension.getSingleUnit() !is CompositeUnit<*>) throw DecompositionException("Target unit is not composite")

    return decomposeUnit(value, unit as CompositeUnit<*>, locale)
}

fun decomposeUnit(value:BigDecimal, unit:CompositeUnit<*>, locale: Locale = Locale.getDefault()) : String {
    val divAndRemainder = value.divideAndRemainder(unit.ratio)
    val divider = divAndRemainder[0].stripTrailingZeros()
    val remainder = divAndRemainder[1].stripTrailingZeros()
    val symbol = unit.symbol(locale)
    return if (remainder == BigDecimal.ZERO) {
        "${divider.out()} $symbol"
    } else {
        if (unit.parentUnit is CompositeUnit) {
            "${divider.out()} $symbol ${decomposeUnit(remainder, unit.parentUnit, locale)}"
        } else {
            "${divider.out()} $symbol ${remainder.divide(unit.parentUnit.ratio, MathContext.DECIMAL64).out()} ${unit.parentUnit.symbol(locale)}"
        }
    }
}