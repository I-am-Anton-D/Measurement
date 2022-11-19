package dimension

import quantity.Quantity
import unit.prototype.AbstractUnit
import java.math.BigDecimal
import java.math.MathContext
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

open class Dimension<Q> private constructor() {
    private val unitsSet: ArrayList<UnitHolder> = ArrayList()

    constructor(vararg holders: UnitHolder) : this() {
        holders.forEach { holder -> addUnit(holder) }
    }

    constructor(vararg units: AbstractUnit<*>) :
            this(*units.map { unit -> UnitHolder(unit) }.toTypedArray())

    operator fun times(other: Dimension<*>): Dimension<Quantity> =
        Dimension(*(unitsSet + other.unitsSet).toTypedArray())

    operator fun div(other: Dimension<*>): Dimension<Quantity> {
        val otherInverse = other.unitsSet.map { uh -> uh.inverse() }
        return Dimension(*(unitsSet + otherInverse).toTypedArray())
    }

    private fun addUnit(unit: UnitHolder) {
        val indexOf = indexOfByUnitQuantity(unit)

        if (indexOf < 0) {
            unitsSet.add(unit)
        } else {
            val rPow = unitsSet[indexOf].pow + unit.pow
            if (rPow == 0) {
                unitsSet.removeAt(indexOf)
            } else {
                unitsSet[indexOf] = unitsSet[indexOf].copyWith(rPow)
            }
        }
    }

    private fun indexOfByUnitQuantity(unit: UnitHolder) : Int {
        unitsSet.forEachIndexed{ index, element ->
            if (element.unitQuantity == unit.unitQuantity) return index
        }
        return -1
    }

    fun convertValue(target: Dimension<Q>, value: Number): BigDecimal {
        if (unitsSet.size != target.unitsSet.size) throw Exception()

        val sortedFrom = unitsSet.sortedBy { uh -> uh.unitQuantity.toString() }
        val sortedTo = target.unitsSet.sortedBy { uh -> uh.unitQuantity.toString() }

        var numerator = BigDecimal.ONE
        var denominator = BigDecimal.ONE

        sortedTo.forEachIndexed { index, toUnit ->
            if (!toUnit.canConvert(sortedFrom[index])) throw Exception()

            val prefixMultiplier = toUnit.prefix.getPrefixMultiplier()
            val unitRatio = toUnit.unit.ratio.multiply(prefixMultiplier).pow(toUnit.pow.absoluteValue)

            if (toUnit.pow > 0) numerator = numerator.multiply(unitRatio, MathContext.DECIMAL128)
            if (toUnit.pow < 0) denominator = denominator.multiply(unitRatio, MathContext.DECIMAL128)
        }

        val rate = denominator.divide(numerator, MathContext.DECIMAL128)

        return BigDecimal(value.toString()).multiply(rate).round(MathContext.DECIMAL128)
    }

    open fun toString(dimensionFormat: DimensionFormat = DimensionFormat.NORMAL, locale: Locale): String {
        when (dimensionFormat) {
            DimensionFormat.NORMAL -> {
                var numerator = ""
                var denominator = ""

                unitsSet.forEach { uh ->
                    val prefix = uh.prefix.prefixSymbol(locale)
                    val symbol = uh.unit.symbol(locale)
                    val powSuperscript = powInSuperScript[uh.pow.absoluteValue]
                    val multiplySign =
                        if ((numerator.isNotEmpty() && uh.pow > 0) || (denominator.isNotEmpty() && uh.pow < 0)) "·" else ""

                    if (uh.pow > 0) numerator += multiplySign + prefix + symbol + powSuperscript
                    if (uh.pow < 0) denominator += multiplySign + prefix + symbol + powSuperscript

                }

                if (numerator.isEmpty() && denominator.isNotEmpty()) numerator = "1"
                if (denominator.isNotEmpty()) denominator = "/$denominator"

                return "$numerator$denominator"
            }

            DimensionFormat.ANSI -> {
                var ansiString = ""
                unitsSet.forEach { uh ->
                    val prefix = uh.prefix.prefixSymbol(locale)
                    val symbol = uh.unit.symbol(locale)
                    val powString = if (uh.pow == 1) "" else "^$uh.pow"
                    val space = (if (ansiString.isNotEmpty()) " " else "")
                    ansiString += space + prefix + symbol + powString
                }

                return ansiString
            }
        }
    }

    override fun toString() = toString(DimensionFormat.NORMAL, Locale.getDefault())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Dimension<*>

        return unitsSet == other.unitsSet
    }

    override fun hashCode() = unitsSet.hashCode()

    companion object {
        val powInSuperScript =
            listOf("", "", "\u00B2", "\u00B3", "\u2074", "\u2075", "\u2076", "\u2077", "\u2078", "\u2079")
    }
}