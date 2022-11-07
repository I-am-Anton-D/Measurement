package unit.prototype

import dimension.Dimension
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

    open fun getBundle(locale: Locale): ResourceBundle {
        val unitSimpleClassName = this::class.simpleName ?: throw NoBundleForAnonymousClass()
        return ResourceBundle.getBundle(unitSimpleClassName, locale)
    }

    open fun toString(expand: Boolean = false, locale: Locale = Locale.getDefault(), value: BigDecimal) =
        if (expand) expandedForm(locale, value)
        else symbol(locale)

    open fun toExpandedString(locale: Locale = Locale.getDefault(), value: BigDecimal) : String {
        return expandedForm(locale, value)
    }

    fun pow(pow: Int) = UnitHolder(this, pow)

    fun toDimensionUnit(): DimensionUnit<Q> {
        if (this is DimensionUnit) return this
        return object : DimensionUnit<Q>() {
            override val dimension = Dimension(this@AbstractUnit)
        }
    }

    override fun toString() = symbol()
}