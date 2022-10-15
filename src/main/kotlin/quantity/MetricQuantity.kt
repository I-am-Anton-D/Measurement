package quantity

import units.AbstractUnit
import units.MetricUnit
import utils.Prefix
import utils.ToStringParameters
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

abstract class MetricQuantity<Q>(number: Number, unit: AbstractUnit<Q>) :
    AbstractQuantity<Q>(number, unit) {

    open fun valueIn(prefix: Prefix = Prefix.NOMINAL, unit: MetricUnit<Q>): BigDecimal {
        return super.valueIn(unit).divide((prefix.getPrefixMultiplier()))
    }

    override fun toString(op: ToStringParameters<Q>): String {
               val targetUnit = op.unit ?: unit
        val valueIn = if (targetUnit is MetricUnit) valueIn(op.prefix, targetUnit) else valueIn(targetUnit)

        val valueString = op.df.format(valueIn)
        val prefixString = op.prefix.getPrefixString(op.expand, op.locale)
        val unitString = targetUnit.toString(op, valueIn)

        return "$valueString $prefixString$unitString"
    }

    fun toString(
        df: DecimalFormat = DecimalFormat(),
        locale: Locale = Locale.getDefault(),
        prefix: Prefix = Prefix.NOMINAL,
        expand: Boolean = false,
        unit: MetricUnit<Q>
    ) = toString(ToStringParameters(df, locale, prefix, expand, unit))

    fun toString(
        df: DecimalFormat = DecimalFormat(),
        locale: Locale = Locale.getDefault(),
        expand: Boolean = false,
        unit: AbstractUnit<Q> = this.unit
    ) = toString(ToStringParameters(df, locale, expand, unit))
}