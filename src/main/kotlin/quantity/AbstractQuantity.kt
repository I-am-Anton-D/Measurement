package quantity

import unit.prototype.*
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.util.*

abstract class AbstractQuantity<Q>(
    val valueInBaseUnit: BigDecimal,
    val baseUnit: AbstractUnit<Q>,
    val useUnit: AbstractUnit<Q>? = null,
    val usePrefix: Prefix? = null
) :
    Comparable<AbstractQuantity<Q>> {

    constructor(number: Number, baseUnit: AbstractUnit<Q>) : this(BigDecimal(number.toString()), baseUnit)

    constructor(number: Number, baseUnit: AbstractUnit<Q>, useUnit: AbstractUnit<Q>?, usePrefix: Prefix?)
            : this(valueInBaseUnit = BigDecimal(number.toString()), baseUnit, useUnit, usePrefix)

    abstract fun copyWith(
        value: BigDecimal,
        useUnit: AbstractUnit<Q>? = null,
        usePrefix: Prefix? = null
    ): AbstractQuantity<Q>

    open fun valueIn(unit: AbstractUnit<Q>): BigDecimal =
        valueInBaseUnit.divide(unit.ratio, MathContext.DECIMAL128)

    open fun valueIn(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Q>): BigDecimal =
        valueIn(unit).divide((prefix.getPrefixMultiplier()))

    open infix fun to(unit: AbstractUnit<Q>) = valueIn(unit)

    open fun toString(op: ToStringParameters<Q>): String {
        val targetUnit = op.unit ?: useUnit ?: baseUnit
        val df = op.df
        val prefix = if (op.normailze) op.prefix ?: usePrefix ?: Prefix.NOMINAL else Prefix.NOMINAL
        val valueIn = if (targetUnit is MetricUnit) valueIn(prefix, targetUnit) else valueIn(targetUnit)
        val unitString = targetUnit.toString(op.expand, op.locale, valueIn)
        val valueString = if (df == null) valueIn.stripTrailingZeros().toPlainString() else df.format(valueIn)
        val space = if (targetUnit is StreakUnit && !op.expand) "" else " "

        when (targetUnit) {
            is MetricUnit -> {
                val prefixString = if (prefix != Prefix.NOMINAL) prefix.getPrefixString(op.expand, op.locale) else ""
                return "$valueString $prefixString$unitString"
            }
            is FractionUnit -> {
                val fractionString = targetUnit.getFractionString(valueIn) ?: valueString
                return "$fractionString$space$unitString"
            }
            is CompositeUnit -> {
                return if (op.normailze) {
                    val divAndRemainder = valueInBaseUnit.divideAndRemainder(targetUnit.ratio)
                    val integer = divAndRemainder[0].stripTrailingZeros().toString()
                    val remainder = divAndRemainder[1].stripTrailingZeros()
                    if (remainder == BigDecimal.ZERO) {
                        "$integer$space$unitString"
                    } else {
                        "$integer$space$unitString$space" + copyWith(remainder, targetUnit.parentUnit).toString(op)
                    }
                } else {
                    "$valueString$space$unitString"
                }
            }

            else -> return "$valueString $unitString"
        }
    }

    open fun toString(
        df: DecimalFormat? = null,
        locale: Locale = Locale.getDefault(),
        prefix: Prefix? = null,
        expand: Boolean = false,
        normalize: Boolean = true,
        unit: AbstractUnit<Q>? = null
    ) = toString(ToStringParameters(df, locale, expand, normalize, prefix, unit))


    override fun toString(): String {
        return toString(expand = false)
    }

    @Suppress("UNCHECKED_CAST")
    open operator fun plus(other: AbstractQuantity<Q>): Q {
        if (this.baseUnit != other.baseUnit) throw Exception()
        return copyWith(valueInBaseUnit + other.valueInBaseUnit, useUnit, usePrefix) as Q
    }

    @Suppress("UNCHECKED_CAST")
    open operator fun minus(other: AbstractQuantity<Q>): Q {
        if (this.baseUnit != other.baseUnit) throw Exception()
        return copyWith(valueInBaseUnit - other.valueInBaseUnit, useUnit, usePrefix) as Q
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
