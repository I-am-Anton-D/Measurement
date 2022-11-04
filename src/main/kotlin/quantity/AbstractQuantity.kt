package quantity

import dimension.Dimension
import unit.prototype.*
import java.math.BigDecimal
import java.util.*

abstract class AbstractQuantity<Q>(val value: BigDecimal) : Comparable<AbstractQuantity<Q>> {
    abstract val baseUnit: AbstractUnit<Q>

    constructor(number: Number) : this(BigDecimal(number.toString()))

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    @Suppress("UNCHECKED_CAST")
    open operator fun plus(other: AbstractQuantity<Q>): Q {
        if (this.baseUnit != other.baseUnit) throw Exception()
        return copyWith(value + other.value) as Q
    }

    @Suppress("UNCHECKED_CAST")
    open operator fun minus(other: AbstractQuantity<Q>): Q {
        if (this.baseUnit != other.baseUnit) throw Exception()
        return copyWith(value - other.value) as Q
    }

    open operator fun times(other: AbstractQuantity<*>): Quantity {
        val dimension = Dimension(baseUnit, other.baseUnit, false)
        val result = this.value * other.value
        return Quantity(result, QuantityUnit(dimension))
    }

    open operator fun div(other: AbstractQuantity<*>): Quantity {
        val dimension = Dimension(baseUnit, other.baseUnit, true)
        val result = this.value / other.value
        return Quantity(result, QuantityUnit(dimension))
    }

    override fun toString() = "${value.toPlainString()} $baseUnit"

    override operator fun compareTo(other: AbstractQuantity<Q>): Int {
        return if (baseUnit == other.baseUnit) {
            this.value.compareTo(other.value)
        } else {
            throw Exception()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractQuantity<*>) return false

        return if (baseUnit == other.baseUnit) {
            this.value == other.value
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + baseUnit.hashCode()
        return result
    }
}
