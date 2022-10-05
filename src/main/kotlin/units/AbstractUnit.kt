package units

import quantity.OutputParameters
import java.math.BigDecimal
import java.util.*
import kotlin.reflect.KClass


abstract class AbstractUnit {
    open fun symbol(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("symbol")

    open fun expandedForm(locale: Locale = Locale.getDefault(), value: BigDecimal) = if (BigDecimal.ONE == value) singularForm(locale) else pluralForm(locale)

    open fun singularForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("singularForm")

    open fun pluralForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("pluralForm")

    open fun getBundle(locale: Locale): ResourceBundle {
        val unitSimpleClassName = this::class.simpleName ?: throw Exception()
        return ResourceBundle.getBundle(unitSimpleClassName, locale) ?: throw Exception()
    }

    open fun convertTo(kClass: KClass<out AbstractUnit>, number: Number): BigDecimal {
        throw NotImplementedError()
    }

    open fun convertFrom(kClass: KClass<out AbstractUnit>, number: Number): BigDecimal {
        throw NotImplementedError()
    }

    open fun toString(outputParameters: OutputParameters, value: BigDecimal): String {
        return if (outputParameters.expand) expandedForm(outputParameters.locale, value)
        else symbol(outputParameters.locale)
    }

    override fun toString() = symbol()


}