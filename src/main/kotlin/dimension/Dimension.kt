package dimension

import unit.prototype.AbstractUnit

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

    open operator fun times(other: Dimension): Dimension {
        val dim = Dimension()
        dim.addSetOfUnits(this.unitsSet)
        dim.addSetOfUnits(other.unitsSet)
        return dim
    }

    private fun addUnit(unit: AbstractUnit<*>, pow: Int = 1) {
        unitsSet.computeIfPresent(unit) { _, v -> v + pow } ?: unitsSet.put(unit, pow)
    }

    private fun addSetOfUnits(unitSet: Map<AbstractUnit<*>, Int>) {
        unitSet.forEach { (k, v) -> addUnit(k, v) }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dimension

        if (this.unitsSet.size != other.unitsSet.size) return false

        val sortedList = unitsSet.toList().sortedBy { it::class.simpleName }
        val otherSortedList = other.unitsSet.toList().sortedBy { it::class.simpleName }

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