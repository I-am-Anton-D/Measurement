package quantity

import dimension.*
import extension.out
import extension.toBigDecimal
import unit.abstract.AbstractUnit
import unit.abstract.MetricUnit
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.util.*

abstract class AbstractQuantity<Q>(val value: BigDecimal, val dimension: Dimension<Q>) : Comparable<AbstractQuantity<Q>> {
    var toStringDimension: Dimension<Q> = dimension

    constructor(number: Number, unit: AbstractUnit<Q>) : this(number.toBigDecimal(), unit.toDimension())

    constructor(number: Number, dimension: Dimension<Q>) : this(number.toBigDecimal(), dimension)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    @Suppress("UNCHECKED_CAST")
    open operator fun plus(other: AbstractQuantity<Q>): Q {
        if (dimension != other.dimension) throw Exception()
        return copyWith(value + other.value) as Q
    }

    @Suppress("UNCHECKED_CAST")
    open operator fun minus(other: AbstractQuantity<Q>): Q {
        if (dimension != other.dimension) throw Exception()
        return copyWith(value - other.value) as Q
    }

    open operator fun times(other: AbstractQuantity<*>) =
        Quantity(value * other.value, dimension * other.dimension)

    open operator fun div(other: AbstractQuantity<*>) =
        Quantity(value.divide(other.value, MathContext.DECIMAL128), dimension / other.dimension)

    open fun valueIn(unit: AbstractUnit<Q>) = valueIn(unit.toDimension())

    open fun valueIn(unit: MetricUnit<Q>, prefix: Prefix = Prefix.NOMINAL) = valueIn(unit.prefix(prefix))

    open fun valueIn(toDimension: Dimension<Q>) = this.dimension.convertValue(value, toDimension)

    open fun toString(unit: AbstractUnit<Q>, valueFormat: DecimalFormat? = null, locale: Locale = Locale.getDefault()) =
        toString(unit.toDimension(), valueFormat, locale)

    open fun toString(
        dimension: Dimension<Q>? = null,
        valueFormat: DecimalFormat? = null,
        locale: Locale = Locale.getDefault()
    ): String {
        val targetDimension = dimension ?: toStringDimension
        val valueIn = valueIn(targetDimension)
        val valueString = valueFormat?.format(valueIn) ?: valueIn.out()
        val unitString = targetDimension.toString(locale)
        return "$valueString $unitString"
    }

    override fun toString() = toString(toStringDimension)

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

        return dimension == other.dimension && this.value == other.value
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + dimension.hashCode()
        return result
    }
}
