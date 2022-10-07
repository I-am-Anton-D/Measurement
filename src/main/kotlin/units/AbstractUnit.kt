package units

import quantity.OutputParameters
import java.math.BigDecimal
import java.util.*


abstract class AbstractUnit<Q> {

    open val ratio = 1.0

    open fun symbol(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("symbol")

    open fun singularForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("singularForm")

    open fun pluralForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("pluralForm")

    open fun expandedForm(locale: Locale = Locale.getDefault(), value: BigDecimal) =
        if (BigDecimal.ONE == value) singularForm(locale) else pluralForm(locale)

    open fun getBundle(locale: Locale): ResourceBundle {
        val unitSimpleClassName = this::class.simpleName ?: throw Exception()
        return ResourceBundle.getBundle(unitSimpleClassName, locale) ?: throw Exception()
    }

    open fun toString(outputParameters: OutputParameters, value: BigDecimal): String {
        return if (outputParameters.expand) expandedForm(outputParameters.locale, value)
        else symbol(outputParameters.locale)
    }

    override fun toString() = symbol()
}