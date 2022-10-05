package units

import quantity.OutputParameters
import java.math.BigDecimal
import java.util.*


abstract class AbstractUnit {
    open fun unitSymbol(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("symbol")

    open fun fullUnitName(locale: Locale = Locale.getDefault(), value: BigDecimal) =  if (BigDecimal.ONE == value) singularForm(locale) else pluralForm(locale)

    open fun singularForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("fullName")

    open fun pluralForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("pluralForm")

    open fun getBundle(locale: Locale): ResourceBundle {
        val unitSimpleClassName = this::class.simpleName ?: throw Exception()
        return ResourceBundle.getBundle(unitSimpleClassName, locale) ?: throw Exception()
    }

    open fun toString(outputParameters: OutputParameters, value: BigDecimal): String {
        return if (outputParameters.fullUnitName) fullUnitName(outputParameters.locale, value)
        else unitSymbol(outputParameters.locale)
    }

    override fun toString() = unitSymbol()

}