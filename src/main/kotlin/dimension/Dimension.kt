package dimension

import unit.prototype.AbstractUnit
import unit.prototype.DimensionUnit

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

    open operator fun times(other: Dimension): Dimension {
        val dim = Dimension()
        dim.addSetOfUnits(this.unitsSet)
        dim.addSetOfUnits(other.unitsSet)
        return dim
    }

    private fun addUnit(unit: AbstractUnit<*>, pow: Int = 1, inverse: Boolean = false) {
        unitsSet.computeIfPresent(unit) { _, v -> v + if (inverse) -pow else pow } ?: unitsSet.put(
            unit,
            if (inverse) -pow else pow
        )
    }

    private fun addSetOfUnits(unitSet: Map<AbstractUnit<*>, Int>, inverse: Boolean = false) {
        unitSet.forEach { (k, v) -> addUnit(k, if (inverse) -v else v) }
    }

    override fun toString(): String {
        var result = ""
        unitsSet.forEach { (k, v) -> result += "$k^$v⋅" }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dimension

        if (this.unitsSet.size != other.unitsSet.size) return false

        val sortedList = unitsSet.toList().sortedBy { it.first::class.simpleName }
        val otherSortedList = other.unitsSet.toList().sortedBy { it.first::class.simpleName }

        for (i in sortedList.indices) {
            val thisElement = sortedList[i]
            val otherElement = otherSortedList[i]

            if (thisElement.first != otherElement.first || thisElement.second != otherElement.second) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var hashcode = 0
        unitsSet.forEach { (k, v) ->
            hashcode += k.hashCode() + v.hashCode()
        }
        return hashcode
    }
}