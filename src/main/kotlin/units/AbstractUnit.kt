package units

import java.math.BigDecimal
import java.util.*

abstract class AbstractUnit<Q>(val ratio: BigDecimal = BigDecimal.ONE) {

    constructor(number: Number) : this(BigDecimal(number.toString()))

    open fun symbol(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("symbol")

    open fun singularForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("singularForm")

    open fun pluralForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("pluralForm")

    open fun expandedForm(locale: Locale = Locale.getDefault(), value: BigDecimal) =
        if (BigDecimal.ONE == value) singularForm(locale) else pluralForm(locale)

    open fun getBundle(locale: Locale): ResourceBundle {
        val unitSimpleClassName = this::class.simpleName ?: throw Exception()
        return ResourceBundle.getBundle(unitSimpleClassName, locale) ?: throw Exception()
    }

    open fun toString(expand:Boolean = false, locale: Locale = Locale.getDefault(), value: BigDecimal) =
        if (expand) expandedForm(locale, value)
        else symbol(locale)

    fun valueInBaseUnit(number: Number): BigDecimal =
        if (ratio == BigDecimal.ONE) BigDecimal(number.toString())
        else BigDecimal(number.toString()).multiply(ratio)

    override fun toString() = symbol()
}

abstract class MetricUnit<Q> : AbstractUnit<Q>()