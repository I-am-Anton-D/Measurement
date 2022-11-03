package dimension

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

    fun getPrefixMultiplier(): BigDecimal = BigDecimal.TEN.pow(exponent, MathContext.DECIMAL128)

    fun getPrefixString(expand: Boolean = false, locale: Locale = Locale.getDefault()) = if (expand) prefixName(locale) else prefixSymbol(locale)

    fun prefixSymbol(locale: Locale = Locale.getDefault()): String =
        getBundle(locale).getString(this.toString() + "_SYMBOL")

    fun prefixName(locale: Locale = Locale.getDefault()): String =
        getBundle(locale).getString(this.toString())

    fun getNominalValue(number: Number): BigDecimal =
        if (this == NOMINAL) BigDecimal(number.toString())
        else BigDecimal(number.toString()).multiply(getPrefixMultiplier())

    private fun getBundle(locale: Locale) = ResourceBundle.getBundle(this::class.simpleName!!, locale)
}