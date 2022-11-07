package dimension

import unit.prototype.AbstractUnit
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

    override fun toString(): String {
        var numerator = ""
        var denominator = ""

        unitsSet.forEach { (unit, pow) ->
            if (pow > 0) {
                numerator += (if (numerator.isNotEmpty()) "·" else "") + unit + convertPowToSuperscript(pow)
            }
            if (pow < 0) {
                denominator += (if (denominator.isNotEmpty()) "·" else "") + unit + convertPowToSuperscript(pow)
            }
        }

        if (numerator.isEmpty()) numerator = "1"

        return "$numerator/$denominator"
    }

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
}