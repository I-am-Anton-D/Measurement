package info.dmitrochenko.measurment.dimension

import info.dmitrochenko.measurment.extension.toBigDecimal
import java.math.BigDecimal
import java.math.MathContext
import java.util.*

enum class Prefix(val exponent: Int) {
    YOTTA(24),
    ZETTA(21),
    EXA(18),
    PETA(15),
    TERA(12),
    GIGA(9),
    MEGA(6),
    KILO(3),
    HECTO(2),
    DEKA(1),
    NOMINAL(0),
    DECI(-1),
    CENTI(-2),
    MILLI(-3),
    MICRO(-6),
    NANO(-9),
    PICO(-12),
    FEMTO(-15),
    ATTO(-18),
    ZEPTO(-21),
    YOCTO(-24);

    fun symbol(locale: Locale = Locale.getDefault()): String =
        getBundle(locale).getString(this.toString() + "_SYMBOL")

    fun name(locale: Locale = Locale.getDefault()): String =
        getBundle(locale).getString(this.toString())

    fun multiplier(): BigDecimal = BigDecimal.TEN.pow(exponent, MathContext.DECIMAL128)

    fun inNominal(number: Number): BigDecimal =
        if (this == NOMINAL) number.toBigDecimal()
        else  number.toBigDecimal().multiply(multiplier())

    private fun getBundle(locale: Locale) = ResourceBundle.getBundle(this::class.simpleName!!, locale)
}