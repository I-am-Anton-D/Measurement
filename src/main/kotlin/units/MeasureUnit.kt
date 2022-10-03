package units

import java.math.BigDecimal
import java.util.*


interface MeasureUnit {
    fun unitSymbol(locale: Locale? = null): String {
        return getBundle(locale).getString("symbol")
    }

    fun fullUnitName(locale: Locale?, value:BigDecimal): String {
        return singularForm(locale)
    }

    fun singularForm(locale: Locale?) : String {
        return getBundle(locale).getString("fullName")
    }

    fun pluralForm(locale: Locale?) : String {
        return getBundle(locale).getString("pluralForm")
    }

    fun getBundle(locale: Locale? = null): ResourceBundle {
        val targetLocale = locale ?: Locale.getDefault()
        val unitSimpleClassName = this::class.simpleName ?: throw Exception()
        return ResourceBundle.getBundle(unitSimpleClassName, targetLocale) ?: throw Exception()
    }
}