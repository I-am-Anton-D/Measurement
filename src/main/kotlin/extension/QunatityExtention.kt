package extension

import dimension.Dimension
import exception.DecompositionException
import exception.FractionalTransformException
import quantity.AbstractQuantity
import unit.abstract.CompositeUnit
import unit.abstract.FractionUnit
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

fun <Q> AbstractQuantity<Q>.toDecomposedString(decomposeFrom:CompositeUnit<Q>? = null, withFraction: Boolean = false, locale: Locale = Locale.getDefault()) : String {
    val targetDimension = decomposeFrom?.toDimension() ?: toStringDimension
    if (!targetDimension.isSingleUnit()) throw DecompositionException("Size of dimension of quantity not equal 1")

    val unit = targetDimension.getSingleUnit()
    if (unit !is CompositeUnit<*>) throw DecompositionException("Target unit is not composite")

    return decomposeUnit(value, unit, withFraction, locale)
}

//TODO NEED HARD TESTING
private fun <Q> decomposeUnit(value:BigDecimal, unit:CompositeUnit<Q>, withFraction: Boolean = false, locale: Locale = Locale.getDefault()) : String {
    val divAndRemainder = value.divideAndRemainder(unit.ratio)
    val divider = divAndRemainder[0].stripTrailingZeros()
    val remainder = divAndRemainder[1].stripTrailingZeros()
    val symbol = unit.symbol(locale)
    return if (remainder == BigDecimal.ZERO) {
        "${divider.out()} $symbol"
    } else {
        if (unit.parentUnit is CompositeUnit) {
            "${divider.out()} $symbol ${decomposeUnit(remainder, unit.parentUnit, withFraction, locale)}"
        } else {
            if (unit.parentUnit is FractionUnit<*> && withFraction) {
                val valueIn = remainder.divide(unit.parentUnit.ratio, MathContext.DECIMAL64)
                return "${divider.out()} $symbol ${unit.parentUnit.getFractionString(valueIn)} ${unit.parentUnit.symbol(locale)}"
            }  else {
                return "${divider.out()} $symbol ${remainder.divide(unit.parentUnit.ratio, MathContext.DECIMAL64).out()} ${unit.parentUnit.symbol(locale)}"
            }
        }
    }
}

//TODO NEED HARD TESTING
@Suppress("UNCHECKED_CAST")
fun <Q> AbstractQuantity<Q>.toFractionalString(fractionalFrom: FractionUnit<Q>? = null, locale: Locale = Locale.getDefault()) : String {
    val targetDimension = fractionalFrom?.toDimension() ?: toStringDimension
    if (!targetDimension.isSingleUnit()) throw FractionalTransformException("Size of dimension quantity not equal 1")

    val unit = targetDimension.getSingleUnit()
    if (unit !is FractionUnit<*>) throw FractionalTransformException("Target unit is not fractional")

    val valueIn = valueIn(unit as FractionUnit<Q>)

    val fractionString = unit.getFractionString(valueIn) ?: valueIn.out()
    return "$fractionString ${unit.symbol(locale)}"
}