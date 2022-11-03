package unit.prototype

import dimension.UnitHolder
import exception.NoBundleForAnonymousClass
import java.math.BigDecimal
import java.util.*

abstract class AbstractUnit<Q>(val ratio: BigDecimal = BigDecimal.ONE) {

    constructor(number: Number) : this(BigDecimal(number.toString()))

    open fun symbol(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("symbol")

    open fun singularForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("singularForm")

    open fun pluralForm(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("pluralForm")

    open fun expandedForm(locale: Locale = Locale.getDefault(), value: BigDecimal): String =
        try {
            getBundle(locale).getString(value.toString())
        } catch (ex: MissingResourceException) {
            pluralForm(locale)
        }

    open fun valueInBaseUnit(number: Number): BigDecimal =
        if (ratio == BigDecimal.ONE) BigDecimal(number.toString())
        else BigDecimal(number.toString()).multiply(ratio)

    open fun getBundle(locale: Locale): ResourceBundle {
        val unitSimpleClassName = this::class.simpleName ?: throw NoBundleForAnonymousClass()
        return ResourceBundle.getBundle(unitSimpleClassName, locale)
    }

    open fun toString(expand: Boolean = false, locale: Locale = Locale.getDefault(), value: BigDecimal) =
        if (expand) expandedForm(locale, value)
        else symbol(locale)

    fun pow(pow: Int) = UnitHolder(this, pow)

    override fun toString() = symbol()
}