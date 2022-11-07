package unit.prototype

import dimension.Dimension

abstract class DimensionUnit<Q> : AbstractUnit<Q>() {
    abstract val dimension: Dimension

    override fun toString(): String {
        return dimension.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuantityUnit

        if (dimension != other.dimension) return false

        return true
    }

    override fun hashCode(): Int {
        return dimension.hashCode()
    }
}