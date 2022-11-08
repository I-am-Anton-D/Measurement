package dimension

import unit.prototype.AbstractUnit
import java.lang.reflect.ParameterizedType
import java.math.BigDecimal
import java.math.MathContext
import java.util.*
import kotlin.collections.LinkedHashMap
import kotlin.math.absoluteValue

open class Dimension private constructor() {
    private val unitsSet: LinkedHashMap<AbstractUnit<*>, Int> = LinkedHashMap()

    constructor(vararg holders: UnitHolder) : this() {
        holders.forEach { holder -> addUnit(holder.unit, holder.pow) }
    }

    constructor(vararg units: AbstractUnit<*>) : this() {
        units.forEach { unit -> addUnit(unit) }
    }

    constructor(unit: AbstractUnit<*>, other: AbstractUnit<*>, divide: Boolean = false) : this() {
        val first = unit.toDimensionUnit()
        val second = other.toDimensionUnit()

        this.addSetOfUnits(first.dimension.unitsSet)
        this.addSetOfUnits(second.dimension.unitsSet, divide)
    }

    private fun addSetOfUnits(unitSet: Map<AbstractUnit<*>, Int>, inverse: Boolean = false) {
        unitSet.forEach { (k, v) -> addUnit(k, v, inverse) }
    }

    private fun addUnit(unit: AbstractUnit<*>, pow: Int = 1, inverse: Boolean = false) {
        val exist = unitsSet[unit]
        val p = if (inverse) -pow else pow
        if (exist == null) {
            unitsSet[unit] = p
        } else {
            val r = exist + p
            if (r == 0) {
                unitsSet.remove(unit)
            } else {
                unitsSet[unit] = r
            }
        }
    }

    open fun toString(locale: Locale): String {
        var numerator = ""
        var denominator = ""

        unitsSet.forEach { (unit, pow) ->
            if (pow > 0) numerator +=
                (if (numerator.isNotEmpty()) "·" else "") + unit.symbol(locale) + convertPowToSuperscript(pow)
            if (pow < 0) denominator +=
                (if (denominator.isNotEmpty()) "·" else "") + unit.symbol(locale) + convertPowToSuperscript(pow)
        }

        if (numerator.isEmpty()) numerator = "1"

        return "$numerator/$denominator"
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

    companion object {
        fun convertValue(from: Dimension, to: Dimension, value:Number): BigDecimal {
            if (canConvert(from, to)) {
                val toIterator = to.unitsSet.iterator()
                var numerator = BigDecimal.ONE
                var denominator = BigDecimal.ONE
                while (toIterator.hasNext()) {
                    val toUnit = toIterator.next()
                    if (toUnit.value > 0) {
                        numerator = numerator.multiply(toUnit.key.ratio, MathContext.DECIMAL128)
                    } else {
                        denominator = denominator.multiply(toUnit.key.ratio, MathContext.DECIMAL128)
                    }
                }
                val rate = denominator.divide(numerator, MathContext.DECIMAL128)
                return BigDecimal(value.toString()).multiply(rate).round(MathContext.DECIMAL128)
            } else {
                throw Exception()
            }
        }

        private fun canConvert(from: Dimension, to: Dimension): Boolean {
            if (from.unitsSet.size != to.unitsSet.size) return false

            val fromIterator = from.unitsSet.iterator()
            val toIterator = to.unitsSet.iterator()

            while (fromIterator.hasNext()) {
                val fromUnit = fromIterator.next()
                val toUnit = toIterator.next()

                if (fromUnit.value != toUnit.value) return false

                val fromType = (fromUnit.key.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
                val toType =  (toUnit.key.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]

                if (fromType != toType) return false
            }

            return true
        }
    }
}