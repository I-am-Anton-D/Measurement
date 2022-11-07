package quantity

import unit.Prefix
import unit.prototype.*
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.util.*

abstract class BaseQuantity<Q>(number: Number) : AbstractQuantity<Q>(number) {
    var defaultToStringParameters = ToStringParameters<Q>()

    abstract override val baseUnit: BaseUnit<Q>

    abstract override fun copyWith(value: BigDecimal): BaseQuantity<Q>

    open fun valueIn(unit: AbstractUnit<Q>): BigDecimal =
        value.divide(unit.ratio, MathContext.DECIMAL128)

    open fun valueIn(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Q>): BigDecimal =
        valueIn(unit).divide((prefix.getPrefixMultiplier()))

    open infix fun to(unit: AbstractUnit<Q>) = valueIn(unit)

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

    open fun toString(op: ToStringParameters<Q>): String {
        val targetUnit = op.unit ?: defaultToStringParameters.unit ?: baseUnit
        val df = op.df
        val prefix =
            if (op.normailze) op.prefix ?: defaultToStringParameters.prefix ?: Prefix.NOMINAL else Prefix.NOMINAL
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
                    val divAndRemainder = value.divideAndRemainder(targetUnit.ratio)
                    val integer = divAndRemainder[0].stripTrailingZeros()
                    val remainder = divAndRemainder[1].stripTrailingZeros()
                    val unitStringForInteger = targetUnit.toString(op.expand, op.locale, integer)
                    if (remainder == BigDecimal.ZERO) {
                        "$integer$space$unitStringForInteger"
                    } else {
                        val copy = copyWith(remainder)
                        copy.defaultToStringParameters.unit = targetUnit.parentUnit
                        "$integer$space$unitStringForInteger$space${copy.toString(op)}"
                    }
                } else {
                    "$valueString$space$unitString"
                }
            }

            else -> return "$valueString $unitString"
        }
    }
}