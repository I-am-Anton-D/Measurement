package quantity

import units.AbstractUnit
import units.Prefix
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

abstract class MetricQuantity<Q>(number: Number, unit: AbstractUnit<Q>) :
    AbstractQuantity<Q>(number, unit) {

    open fun valueIn(
        prefix: Prefix = Prefix.NOMINAL,
        unit: AbstractUnit<Q> = this.unit
    ): BigDecimal {
        return super.valueIn(unit).divide((prefix.getPrefixMultiplier()))
    }

    override fun toString(outputParameters: OutputParameters<Q>): String {
        val prefix = outputParameters.prefix
        val locale = outputParameters.locale
        val targetUnit = outputParameters.unit ?: unit

        val valueIn = valueIn(prefix, targetUnit)

        val valueString = outputParameters.df.format(valueIn)
        val prefixString = if (outputParameters.expand) prefix.prefixName(locale) else prefix.prefixSymbol(locale)
        val unitString = targetUnit.toString(outputParameters, valueIn)

        return "$valueString $prefixString$unitString"
    }

    fun toString(
        df: DecimalFormat = DecimalFormat(),
        locale: Locale = Locale.getDefault(),
        prefix: Prefix = Prefix.NOMINAL,
        expand: Boolean = false,
        unit: AbstractUnit<Q>? = null
    ) = toString(OutputParameters(df, locale, prefix, expand, unit))
}