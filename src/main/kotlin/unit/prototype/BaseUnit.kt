package unit.prototype

import dimension.Dimension


abstract class BaseUnit<Q> : AbstractUnit<Q>() {
    abstract val dimension: Dimension

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as BaseUnit<*>

        if (dimension != other.dimension) return false

        return true
    }

    override fun hashCode(): Int {
        return dimension.hashCode()
    }
}