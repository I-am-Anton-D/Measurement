package unit.prototype

import dimension.Dimension
import java.util.*

abstract class DimensionUnit<Q> : AbstractUnit<Q>() {
    abstract val dimension: Dimension

    override fun toString() =  toString(Locale.getDefault())

    open fun toString(locale: Locale) = dimension.toString(locale)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuantityUnit

        return dimension == other.dimension
    }

    override fun hashCode(): Int {
        return dimension.hashCode()
    }
}