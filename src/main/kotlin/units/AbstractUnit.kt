package units

import quantity.OutputParameters
import java.math.BigDecimal
import java.util.*


abstract class AbstractUnit {
    open fun unitSymbol(locale: Locale? = null): String {
        return getBundle(locale).getString("symbol")
    }

    open fun fullUnitName(locale: Locale?, value: BigDecimal): String {
        return singularForm(locale)
    }

    open fun singularForm(locale: Locale?): String {
        return getBundle(locale).getString("fullName")
    }

    open fun pluralForm(locale: Locale?): String {
        return getBundle(locale).getString("pluralForm")
    }

    open fun getBundle(locale: Locale? = null): ResourceBundle {
        val targetLocale = locale ?: Locale.getDefault()
        val unitSimpleClassName = this::class.simpleName ?: throw Exception()
        return ResourceBundle.getBundle(unitSimpleClassName, targetLocale) ?: throw Exception()
    }

    open fun toString(outputParameters: OutputParameters): String {
        return if (outputParameters.fullUnitName) pluralForm(outputParameters.locale)
        else unitSymbol(outputParameters.locale)
    }

    override fun toString(): String {
        return unitSymbol()
    }
}