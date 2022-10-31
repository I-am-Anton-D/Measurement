package quantity

import unit.prototype.AbstractUnit
import unit.prototype.FractionUnit
import unit.prototype.MetricUnit
import unit.prototype.StreakUnit
import util.Prefix
import util.ToStringParameters
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.util.*

abstract class AbstractQuantity<Q>(val valueInBaseUnit: BigDecimal, val baseUnit: AbstractUnit<Q>) :
    Comparable<AbstractQuantity<Q>> {

    constructor(number: Number, unit: AbstractUnit<Q>) : this(BigDecimal(number.toString()), unit)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    open fun valueIn(unit: AbstractUnit<Q>): BigDecimal =
        valueInBaseUnit.divide(unit.ratio, MathContext.DECIMAL128)

    open fun valueIn(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Q>): BigDecimal =
        valueIn(unit).divide((prefix.getPrefixMultiplier()))

    open infix fun to(unit: AbstractUnit<Q>) = valueIn(unit)

    open fun toString(op: ToStringParameters<Q>): String {
        val targetUnit = op.unit ?: baseUnit
        val df = op.df

        val valueIn = if (targetUnit is MetricUnit) valueIn(op.prefix, targetUnit) else valueIn(targetUnit)
        var valueString = ""

        if (targetUnit is FractionUnit) {
            valueString = targetUnit.getFractionString(valueIn)
        }
        if (valueString.isEmpty()) {
            valueString = if (df == null) valueIn.stripTrailingZeros().toString() else df.format(valueIn)
        }

        val prefixString = op.prefix.getPrefixString(op.expand, op.locale)
        val unitString = targetUnit.toString(op.expand, op.locale, valueIn)
        val space = if (targetUnit is StreakUnit && !op.expand) "" else " "

        return "$valueString$space$prefixString$unitString"
    }

    open fun toString(
        df: DecimalFormat? = null,
        locale: Locale = Locale.getDefault(),
        prefix: Prefix = Prefix.NOMINAL,
        expand: Boolean = false,
        unit: MetricUnit<Q>
    ) = toString(ToStringParameters(df, locale, prefix, expand, unit))

    open fun toString(
        df: DecimalFormat? = null,
        locale: Locale = Locale.getDefault(),
        expand: Boolean = false,
        unit: AbstractUnit<Q> = this.baseUnit
    ) = toString(ToStringParameters(df, locale, expand, unit))

    override fun toString(): String {
        return toString(expand = false)
    }

    @Suppress("UNCHECKED_CAST")
    open operator fun plus(other: AbstractQuantity<Q>): Q {
        if (this.baseUnit != other.baseUnit) throw Exception()
        return copyWith(this.valueInBaseUnit + other.valueInBaseUnit) as Q
    }

    @Suppress("UNCHECKED_CAST")
    open operator fun minus(other: AbstractQuantity<Q>): Q {
        if (this.baseUnit != other.baseUnit) throw Exception()
        return copyWith(this.valueInBaseUnit - other.valueInBaseUnit) as Q
    }

    override operator fun compareTo(other: AbstractQuantity<Q>): Int {
        return if (baseUnit == other.baseUnit) {
            this.valueInBaseUnit.compareTo(other.valueInBaseUnit)
        } else {
            throw Exception()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractQuantity<*>) return false

        return if (baseUnit == other.baseUnit) {
            this.valueInBaseUnit == other.valueInBaseUnit
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = valueInBaseUnit.hashCode()
        result = 31 * result + baseUnit.hashCode()
        return result
    }
}
