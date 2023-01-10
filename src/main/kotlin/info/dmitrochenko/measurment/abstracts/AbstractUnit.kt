package info.dmitrochenko.measurment.abstracts

import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.dimension.UnitHolder
import info.dmitrochenko.measurment.exception.NoBundleForAnonymousClassException
import java.math.BigDecimal
import java.util.*

abstract class AbstractUnit<Q>(val ratio: BigDecimal = BigDecimal.ONE, val zeroOffset: BigDecimal= BigDecimal.ZERO) {

    constructor(number: Number) : this(BigDecimal(number.toString()))

    open operator fun times(other: AbstractUnit<*>) = toDimension() * other.toDimension()

    open operator fun times(other: Dimension<*>) = toDimension() * other

    open operator fun div(other: AbstractUnit<*>) = toDimension() / other.toDimension()

    open operator fun div(other: Dimension<*>) = toDimension() / other

    fun toDimension() = Dimension<Q>(this)

    fun pow(pow: Int = 1) = Dimension<Q>(UnitHolder(this, pow))

    open fun resolveZeroOffset(fromUnit: AbstractUnit<*>, toUnit: AbstractUnit<*>) = fromUnit.zeroOffset - toUnit.zeroOffset

    open fun symbol(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("symbol")

    open fun name(locale: Locale = Locale.getDefault()): String = getBundle(locale).getString("singularForm")

    open fun toString(locale: Locale = Locale.getDefault()) = symbol(locale)

    override fun toString() = symbol()

    open fun getBundle(locale: Locale): ResourceBundle {
        val className = this::class.simpleName ?: throw NoBundleForAnonymousClassException()
        return ResourceBundle.getBundle(className, locale)
    }
}