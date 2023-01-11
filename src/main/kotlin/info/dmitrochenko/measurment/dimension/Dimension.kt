package info.dmitrochenko.measurment.dimension

import info.dmitrochenko.measurment.exception.ConvertDimensionException
import info.dmitrochenko.measurment.exception.NotSingleUnitDimensionException
import info.dmitrochenko.measurment.extension.toBigDecimal

import info.dmitrochenko.measurment.abstracts.AbstractUnit
import info.dmitrochenko.measurment.quantity.Quantity
import java.math.BigDecimal
import java.math.MathContext
import java.util.*
import kotlin.math.absoluteValue

open class Dimension<Q> {
    private val units: ArrayList<UnitHolder> = ArrayList()

    constructor(vararg holders: UnitHolder) {
        holders.forEach { holder -> addUnitHolder(holder) }
    }

    constructor(vararg dimensions: Dimension<*>) : this(*dimensions.flatMap { d -> d.units }.toTypedArray())
    constructor(vararg units: AbstractUnit<*>) : this(*units.map { unit -> UnitHolder(unit) }.toTypedArray())

    operator fun times(other: Dimension<*>) = Dimension<Quantity>(*(units + other.units).toTypedArray())
    operator fun times(other: AbstractUnit<*>) = this * other.toDimension()

    operator fun div(other: Dimension<*>) = Dimension<Quantity>(*(units + other.units.map { uh -> uh.invert() }).toTypedArray())
    operator fun div(other: AbstractUnit<*>) = this / other.toDimension()

    private fun addUnitHolder(unit: UnitHolder) {
        val indexOf = units.indexOfFirst { it.unitQuantity == unit.unitQuantity }

        if (indexOf < 0) {
            units.add(unit)
        } else {
            val rPow = units[indexOf].pow + unit.pow
            if (rPow == 0) {
                units.removeAt(indexOf)
            } else {
                units[indexOf] = units[indexOf].copyWith(rPow)
            }
        }
    }

    fun getUnitList() : ArrayList<UnitHolder> = ArrayList(units)

    fun isSingleUnit() = units.size == 1

    fun isMultiUnit() = units.size > 1

    fun getSingleUnit() = if (isMultiUnit()) throw NotSingleUnitDimensionException() else units[0].unit

    fun convertValue(value: Number, unit: AbstractUnit<Q>) = convertValue(value, unit.toDimension())

    open fun convertValue(value: Number, target: Dimension<Q>): BigDecimal {
        if (units.size != target.units.size)
            throw ConvertDimensionException("$this and $target have different count of units")

        val sortedFrom = units.sortedBy { uh -> uh.unitQuantity.toString() }
        val sortedTo = target.units.sortedBy { uh -> uh.unitQuantity.toString() }

        var numerator = BigDecimal.ONE
        var denominator = BigDecimal.ONE

        for (index in sortedTo.indices) {
            val toUnit = sortedTo[index]
            val fromUnit = sortedFrom[index]

            if (!toUnit.canConvert(fromUnit))
                throw ConvertDimensionException("Can not convert $fromUnit to $toUnit")

            val unitRatio = fromUnit.calculateConvertRatio(toUnit)
            if (toUnit.pow > 0) numerator = numerator.multiply(unitRatio, MathContext.DECIMAL128)
            if (toUnit.pow < 0) denominator = denominator.multiply(unitRatio, MathContext.DECIMAL128)
        }

        val offsetValue = moveZero(value, sortedFrom, sortedTo)
        val rate = numerator.divide(denominator, MathContext.DECIMAL128)
        return offsetValue.multiply(rate).round(MathContext.DECIMAL64)
    }

    open fun moveZero(value: Number, from: List<UnitHolder>, to: List<UnitHolder>): BigDecimal {
        var offsetValue = value.toBigDecimal()

        if (from.size == 1) {
            val fromUnit = from[0].unit
            val toUnit = to[0].unit

            //Fahrenheit hack, try to simplify (for usual units offsetValue = fromUnit.zeroOffset - toUnit.zeroOffset)
            if (toUnit.zeroOffset != BigDecimal.ZERO && fromUnit.zeroOffset != BigDecimal.ZERO) {
                offsetValue += toUnit.zeroOffset + fromUnit.zeroOffset // or maybe throw Exception
            } else if (toUnit.zeroOffset != BigDecimal.ZERO) {
                offsetValue += toUnit.resolveZeroOffset(fromUnit, toUnit)
            } else if (fromUnit.zeroOffset != BigDecimal.ZERO) {
                offsetValue += fromUnit.resolveZeroOffset(fromUnit, toUnit)
            }
        }

        return offsetValue
    }

    open fun toString(locale: Locale = Locale.getDefault()): String {
        var numerator = ""
        var denominator = ""

        units.forEach { uh ->
            val prefix = uh.prefix.symbol(locale)
            val symbol = uh.unit.symbol(locale)
            val powSuperscript = powInSuperScript[uh.pow.absoluteValue]
            val multiplySign = if ((numerator.isNotEmpty() && uh.pow > 0) || (denominator.isNotEmpty() && uh.pow < 0)) "·" else ""

            if (uh.pow > 0) numerator += multiplySign + prefix + symbol + powSuperscript
            if (uh.pow < 0) denominator += multiplySign + prefix + symbol + powSuperscript
        }

        if (numerator.isEmpty() && denominator.isNotEmpty()) numerator = "1"
        if (denominator.isNotEmpty()) denominator = "/$denominator"

        return "$numerator$denominator"
    }

    override fun toString() = toString(Locale.getDefault())

    override fun equals(other: Any?) = this === other || (other is Dimension<*> && units == other.units)

    override fun hashCode() = units.hashCode()

    companion object {
        val powInSuperScript = listOf("", "", "\u00B2", "\u00B3", "\u2074", "\u2075", "\u2076", "\u2077", "\u2078", "\u2079")
    }
}