package dimension

import unit.prototype.AbstractUnit

class UnitHolder(val unit: AbstractUnit<*>, var pow: Int) : Comparable<UnitHolder> {

    override fun compareTo(other: UnitHolder): Int {
        val compare = this.pow.compareTo(other.pow)
        return if (compare == 0) {
            this.unit::class.simpleName!!.compareTo(other.unit::class.simpleName!!)
        } else {
            compare
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnitHolder

        if (unit != other.unit) return false

        return true
    }

    override fun hashCode(): Int {
        return unit.hashCode()
    }
}