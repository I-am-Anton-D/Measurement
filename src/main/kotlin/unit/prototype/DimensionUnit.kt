package unit.prototype

import dimension.Dimension
import java.math.BigDecimal
import java.util.*

abstract class DimensionUnit<Q> : AbstractUnit<Q>() {
    abstract val dimension: Dimension<Q>

  //  override fun toString() =  toString(Locale.getDefault())

   // open fun toString(locale: Locale) = dimension.toString(locale)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuantityUnit

        return dimension == other.dimension
    }

    override fun hashCode(): Int {
        return dimension.hashCode()
    }

    fun convertValueToDimension(target: Dimension<Q>, value: Number): BigDecimal {
        return dimension.convertValue(target, value)
    }
}