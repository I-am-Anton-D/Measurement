package quantity

import dimension.Dimension
import dimension.UnitHolder
import unit.Prefix
import unit.prototype.*
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.util.*

abstract class AbstractQuantity<Q>(val value: BigDecimal, val dimension: Dimension<Q>) :
    Comparable<AbstractQuantity<Q>> {

    constructor(number: Number, unit: AbstractUnit<Q>) : this(BigDecimal(number.toString()), unit.toDimension())

    constructor(number: Number, dimension: Dimension<Q>) : this(BigDecimal(number.toString()), dimension)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    @Suppress("UNCHECKED_CAST")
    open operator fun plus(other: AbstractQuantity<Q>): Q {
        if (this.dimension != other.dimension) throw Exception()
        return copyWith(value + other.value) as Q
    }

    @Suppress("UNCHECKED_CAST")
    open operator fun minus(other: AbstractQuantity<Q>): Q {
        if (this.dimension != other.dimension) throw Exception()
        return copyWith(value - other.value) as Q
    }

    open operator fun times(other: AbstractQuantity<*>): Quantity {
        val dimension = Dimension<Quantity>(dimension, other.dimension, false)
        val result = this.value * other.value
        return Quantity(result, dimension)
    }

    open operator fun div(other: AbstractQuantity<*>): Quantity {
        val dimension = Dimension<Quantity>(dimension, other.dimension, true)
        val result = value.divide(other.value, MathContext.DECIMAL128)
        return Quantity(result, dimension)
    }

    open fun valueIn(unit: AbstractUnit<Q>): BigDecimal =
        valueIn(unit.toDimension())

    open fun valueIn(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Q>): BigDecimal {
        return valueIn(Dimension(UnitHolder(unit, pow = 1, prefix)))
    }

    open fun valueIn(dimension: Dimension<Q>) =
        this.dimension.convertValue(dimension, value)

    open fun toString(
        unit: AbstractUnit<Q>,
        df: DecimalFormat? = null,
        locale: Locale = Locale.getDefault()
    ): String {
        return toString(unit.toDimension(), df, locale)
    }

    open fun toString(
        dimension: Dimension<Q>? = null,
        df: DecimalFormat? = null,
        locale: Locale = Locale.getDefault()
    ): String {
        val valueIn = if (dimension == null) value else valueIn(dimension)
        val valueString = df?.format(valueIn) ?: valueIn.stripTrailingZeros().toPlainString()
        val unitString = dimension?.toString(locale) ?: this.dimension.toString(locale)
        return "$valueString $unitString"
    }

    override fun toString() = "${value.stripTrailingZeros().toPlainString()} $dimension"

    override operator fun compareTo(other: AbstractQuantity<Q>): Int {
        return if (dimension == other.dimension) {
            this.value.compareTo(other.value)
        } else {
            throw Exception()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractQuantity<*>) return false

        return if (dimension == other.dimension) {
            this.value == other.value
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + dimension.hashCode()
        return result
    }
}
