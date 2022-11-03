package quantity

import dimension.Dimension
import unit.prototype.*
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.util.*

abstract class AbstractQuantity<Q>(val value: BigDecimal) : Comparable<AbstractQuantity<Q>> {
    abstract val dimension: Dimension

    var defaultToStringParameters = ToStringParameters<Q>()

    constructor(number: Number) : this(BigDecimal(number.toString()))

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    open fun valueIn(unit: AbstractUnit<Q>): BigDecimal =
        value.divide(unit.ratio, MathContext.DECIMAL128)

    open fun valueIn(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Q>): BigDecimal =
        valueIn(unit).divide((prefix.getPrefixMultiplier()))

    open infix fun to(unit: AbstractUnit<Q>) = valueIn(unit)

    open fun toString(op: ToStringParameters<Q>): String {
//        val targetUnit = op.unit ?: defaultToStringParameters.unit ?: baseUnit
//        val df = op.df
//        val prefix =
//            if (op.normailze) op.prefix ?: defaultToStringParameters.prefix ?: Prefix.NOMINAL else Prefix.NOMINAL
//        val valueIn = if (targetUnit is MetricUnit) valueIn(prefix, targetUnit) else valueIn(targetUnit)
//        val unitString = targetUnit.toString(op.expand, op.locale, valueIn)
//        val valueString = if (df == null) valueIn.stripTrailingZeros().toPlainString() else df.format(valueIn)
//        val space = if (targetUnit is StreakUnit && !op.expand) "" else " "
//
//        when (targetUnit) {
//            is MetricUnit -> {
//                val prefixString = if (prefix != Prefix.NOMINAL) prefix.getPrefixString(op.expand, op.locale) else ""
//                return "$valueString $prefixString$unitString"
//            }
//            is FractionUnit -> {
//                val fractionString = targetUnit.getFractionString(valueIn) ?: valueString
//                return "$fractionString$space$unitString"
//            }
//            is CompositeUnit -> {
//                return if (op.normailze) {
//                    val divAndRemainder = value.divideAndRemainder(targetUnit.ratio)
//                    val integer = divAndRemainder[0].stripTrailingZeros()
//                    val remainder = divAndRemainder[1].stripTrailingZeros()
//                    val unitStringForInteger = targetUnit.toString(op.expand, op.locale, integer)
//                    if (remainder == BigDecimal.ZERO) {
//                        "$integer$space$unitStringForInteger"
//                    } else {
//                        val copy = copyWith(remainder)
//                        copy.defaultToStringParameters.unit = targetUnit.parentUnit
//                        "$integer$space$unitStringForInteger$space${copy.toString(op)}"
//                    }
//                } else {
//                    "$valueString$space$unitString"
//                }
//            }
//
//            else -> return "$valueString $unitString"
//        }
        return ""
    }

    open fun toString(
        unit: AbstractUnit<Q>? = null,
        prefix: Prefix? = null,
        expand: Boolean = false,
        normalize: Boolean = true,
        df: DecimalFormat? = null,
        locale: Locale = Locale.getDefault()
    ) = toString(ToStringParameters(unit, prefix, expand, normalize, df, locale))

    override fun toString(): String {
        return toString(expand = false)
    }

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

    open operator fun times(other: AbstractQuantity<*>): AbstractQuantity<*> {
        val newDimension = dimension * other.dimension
        val newValue = value * other.value
        val clazz = QuantityFactory.getMeasurandByDimension(newDimension)

        return if (clazz == null) Quantity(newValue, newDimension)
        else QuantityFactory.createInstanceOf(clazz, newValue)
    }

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
