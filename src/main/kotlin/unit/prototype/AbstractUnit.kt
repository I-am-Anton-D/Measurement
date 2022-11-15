package unit.prototype

import dimension.Dimension
import dimension.UnitHolder
import exception.NoBundleForAnonymousClass
import quantity.Quantity
import java.math.BigDecimal
import java.util.*

abstract class AbstractUnit<Q>(val ratio: BigDecimal = BigDecimal.ONE) {

    constructor(number: Number) : this(BigDecimal(number.toString()))

    open operator fun times(other: AbstractUnit<*>) = pow(1) * other.pow(1)

    open operator fun div(other: AbstractUnit<*>) = pow(1) / other.pow(1)

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

    override fun toString() = symbol()

    fun pow(pow: Int = 1) = UnitHolder(this, pow)

    fun toDimension() = Dimension<Q>(this)

    fun toDimensionUnit(): DimensionUnit<Q> {
        if (this is DimensionUnit) return this
        return object : DimensionUnit<Q>() {
            override val dimension: Dimension<Q> = Dimension<Q>(this@AbstractUnit)
        }
    }
}