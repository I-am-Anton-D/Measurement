package dimension

import unit.prototype.AbstractUnit
import java.lang.reflect.ParameterizedType
import java.math.BigDecimal
import java.math.MathContext
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

open class Dimension private constructor() {
    private val unitsSet: ArrayList<UnitHolder> = ArrayList()

    constructor(vararg holders: UnitHolder) : this() {
        holders.forEach { holder -> addUnit(holder) }
    }

    constructor(vararg units: AbstractUnit<*>) : this() {
        units.forEach { unit -> addUnit(UnitHolder(unit)) }
    }

    constructor(unit: AbstractUnit<*>, other: AbstractUnit<*>, divide: Boolean = false) : this() {
        val first = unit.toDimensionUnit()
        val second = other.toDimensionUnit()

        this.addSetOfUnits(first.dimension.unitsSet)
        this.addSetOfUnits(second.dimension.unitsSet, divide)
    }

    private fun addSetOfUnits(unitSet: List<UnitHolder>, inverse: Boolean = false) {
        unitSet.forEach { uh -> addUnit(uh, inverse) }
    }

    private fun addUnit(unit: UnitHolder, inverse: Boolean = false) {
        val exist = unitsSet.find { it.unit == unit.unit }

        if (exist == null) {
            unitsSet.add(unit)
        } else {
            val p = if (inverse) -unit.pow else unit.pow
            val r = exist.pow + p
            if (r == 0) {
                unitsSet.remove(exist)
            } else {
                exist.pow = r
            }
        }
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
        when (pow.absoluteValue) {
            2 -> return "\u00B2"
            3 -> return "\u00B3"
            4 -> return "\u2074"
            5 -> return "\u2075"
            6 -> return "\u2076"
            7 -> return "\u2077"
            8 -> return "\u2078"
            9 -> return "\u2079"
        }
        return ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dimension

        return unitsSet == other.unitsSet
    }

    override fun hashCode() = unitsSet.hashCode()

    fun convertValue(target: Dimension, value: Number): BigDecimal {
        if (canConvert(target)) {
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
                    numerator = numerator.multiply(unit.ratio.multiply(prefixMultiplier).pow(pow), MathContext.DECIMAL128)
                } else {
                    denominator = denominator.multiply(unit.ratio.multiply(prefixMultiplier).pow(pow.absoluteValue), MathContext.DECIMAL128)
                }
            }
            val rate = denominator.divide(numerator, MathContext.DECIMAL128)
            return BigDecimal(value.toString()).multiply(rate).round(MathContext.DECIMAL128)
        } else {
            throw Exception()
        }
    }

    private fun canConvert(target: Dimension): Boolean {
        if (unitsSet.size != target.unitsSet.size) return false

        val fromIterator = unitsSet.iterator()
        val toIterator = target.unitsSet.iterator()

        while (fromIterator.hasNext()) {
            val fromUnit = fromIterator.next()
            val toUnit = toIterator.next()

            if (fromUnit.pow != toUnit.pow) return false

            //Kotlin hack
            val fromType = (fromUnit.unit.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
            val toType = (toUnit.unit.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]

            if (fromType != toType) return false
        }

        return true
    }
}