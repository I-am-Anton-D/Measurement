package dimension

import unit.prototype.AbstractUnit
import unit.prototype.DimensionUnit
import kotlin.math.absoluteValue

open class Dimension() {
    private val unitsSet: LinkedHashMap<AbstractUnit<*>, Int> = LinkedHashMap()

    constructor(vararg holders: UnitHolder) : this() {
        for (holder in holders) addUnit(holder.unit, holder.pow)
    }

    constructor(vararg units: AbstractUnit<*>) : this() {
        for (unit in units) {
            addUnit(unit)
        }
    }

    constructor(unit: AbstractUnit<*>, other: AbstractUnit<*>, divide: Boolean = false) : this() {
        if (unit is DimensionUnit && other is DimensionUnit) {
            this.addSetOfUnits(unit.dimension.unitsSet)
            this.addSetOfUnits(other.dimension.unitsSet, divide)
            return
        }

        if (unit is DimensionUnit && other !is DimensionUnit) {
            this.addSetOfUnits(unit.dimension.unitsSet)
            this.addUnit(other, 1, divide)
            return
        }

        if (unit !is DimensionUnit && other is DimensionUnit) {
            this.addUnit(unit)
            this.addSetOfUnits(other.dimension.unitsSet, divide)
            return
        }

        if (unit !is DimensionUnit && other !is DimensionUnit) {
            this.addUnit(unit)
            this.addUnit(other, 1, divide)
        }
    }

    private fun addUnit(unit: AbstractUnit<*>, pow: Int = 1, inverse: Boolean = false) {
        unitsSet.computeIfPresent(unit) { _, v -> v + if (inverse) -pow else pow } ?:
        unitsSet.put(unit, if (inverse) -pow else pow)
    }

    private fun addSetOfUnits(unitSet: Map<AbstractUnit<*>, Int>, inverse: Boolean = false) {
        unitSet.forEach { (k, v) -> addUnit(k, if (inverse) -v else v) }
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

        if (this.unitsSet != other.unitsSet) return false

        return true
    }

    override fun hashCode() = unitsSet.hashCode()
}