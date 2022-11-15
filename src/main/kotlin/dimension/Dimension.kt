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
        val exist = unitsSet.find { it.unit == unit.unit }

        if (exist == null) {
            unitsSet.add(unit)
        } else {
            exist.pow += unit.pow
            if (exist.pow == 0) {
                unitsSet.remove(exist)
            }
        }
    }

    fun convertValue(target: Dimension<Q>, value: Number): BigDecimal {
        if (!canConvert(target)) throw Exception()

        val toIterator = target.unitsSet.iterator()
        var numerator = BigDecimal.ONE
        var denominator = BigDecimal.ONE
        while (toIterator.hasNext()) {
            val toUnit = toIterator.next()
            val pow = toUnit.pow
            val prefix = toUnit.prefix
            val prefixMultiplier = prefix.getPrefixMultiplier()
            val unit = toUnit.unit
            if (pow > 0) {
                numerator =
                    numerator.multiply(unit.ratio.multiply(prefixMultiplier).pow(pow), MathContext.DECIMAL128)
            } else {
                denominator = denominator.multiply(
                    unit.ratio.multiply(prefixMultiplier).pow(pow.absoluteValue),
                    MathContext.DECIMAL128
                )
            }
        }
        val rate = denominator.divide(numerator, MathContext.DECIMAL128)
        return BigDecimal(value.toString()).multiply(rate).round(MathContext.DECIMAL128)
    }

    private fun canConvert(target: Dimension<Q>): Boolean {
        if (unitsSet.size != target.unitsSet.size) return false

        val sortedFrom = unitsSet.sortedBy { uh -> uh.unitQuantity.toString() }
        val sortedTo = target.unitsSet.sortedBy { uh -> uh.unitQuantity.toString() }

        sortedFrom.forEachIndexed { index, from ->
            if (!from.canConvert(sortedTo[index])) return false
        }

        return true
    }

    open fun toString(locale: Locale): String {
        var numerator = ""
        var denominator = ""

        unitsSet.forEach { uh ->
            val prefix = uh.prefix.prefixSymbol(locale)
            val symbol = uh.unit.symbol(locale)

            if (uh.pow > 0) numerator +=
                (if (numerator.isNotEmpty()) "·" else "") + prefix + symbol + convertPowToSuperscript(uh.pow)
            if (uh.pow < 0) denominator +=
                (if (denominator.isNotEmpty()) "·" else "") + prefix + symbol + convertPowToSuperscript(uh.pow)
        }

        if (numerator.isEmpty() && denominator.isNotEmpty()) numerator = "1"
        if (denominator.isNotEmpty()) denominator = "/$denominator"

        return "$numerator$denominator"
    }

    override fun toString() = toString(Locale.getDefault())

    private fun convertPowToSuperscript(pow: Int): String {
        return when (pow.absoluteValue) {
            2 -> "\u00B2"
            3 -> "\u00B3"
            4 -> "\u2074"
            5 -> "\u2075"
            6 -> "\u2076"
            7 -> "\u2077"
            8 -> "\u2078"
            9 -> "\u2079"
            else -> ""
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Dimension<*>

        return unitsSet == other.unitsSet
    }

    override fun hashCode() = unitsSet.hashCode()

}