package quantity

import units.AbstractUnit
import units.MetricUnit
import utils.Prefix
import utils.ToStringParameters
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.util.*

abstract class AbstractQuantity<Q>(val value: BigDecimal, val unit: AbstractUnit<Q>) : Comparable<AbstractQuantity<Q>> {

    constructor(number: Number, unit: AbstractUnit<Q>) : this(BigDecimal(number.toString()), unit)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    open fun valueIn(unit: AbstractUnit<Q>): BigDecimal =
        value.divide(unit.ratio, MathContext.DECIMAL128)

    open fun valueIn(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Q>): BigDecimal =
        valueIn(unit).divide((prefix.getPrefixMultiplier()))

    open infix fun to(unit: AbstractUnit<Q>) = valueIn(unit)

    open fun toString(op: ToStringParameters<Q>): String {
        val targetUnit = op.unit ?: unit
        val valueIn = if (targetUnit is MetricUnit) valueIn(op.prefix, targetUnit) else valueIn(targetUnit)

        val valueString = op.df.format(valueIn)
        val prefixString = op.prefix.getPrefixString(op.expand, op.locale)
        val unitString = targetUnit.toString(op.expand, op.locale, valueIn)

        return "$valueString $prefixString$unitString"
    }

    open fun toString(
        df: DecimalFormat = DecimalFormat(),
        locale: Locale = Locale.getDefault(),
        prefix: Prefix = Prefix.NOMINAL,
        expand: Boolean = false,
        unit: MetricUnit<Q>
    ) = toString(ToStringParameters(df, locale, prefix, expand, unit))

    open fun toString(
        df: DecimalFormat = DecimalFormat(),
        locale: Locale = Locale.getDefault(),
        expand: Boolean = false,
        unit: AbstractUnit<Q> = this.unit
    ) = toString(ToStringParameters(df, locale, expand, unit))

    @Suppress("UNCHECKED_CAST")
    open operator fun plus(other: AbstractQuantity<Q>): Q {
        if (this.unit != other.unit) throw Exception()
        return copyWith(this.value + other.value) as Q
    }

    override operator fun compareTo(other: AbstractQuantity<Q>): Int {
        return if (unit == other.unit) {
            this.value.compareTo(other.value)
        } else {
            throw Exception()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractQuantity<*>) return false

        return if (unit == other.unit) {
            this.value == other.value
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + unit.hashCode()
        return result
    }
}
