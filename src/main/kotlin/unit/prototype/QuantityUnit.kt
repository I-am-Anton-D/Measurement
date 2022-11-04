package unit.prototype

import dimension.Dimension
import quantity.Quantity

class QuantityUnit(override val dimension: Dimension) : DimensionUnit<Quantity>() {

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



