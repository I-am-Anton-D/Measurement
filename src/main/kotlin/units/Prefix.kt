package units

import java.math.BigDecimal
import java.util.*
import kotlin.math.absoluteValue

enum class Prefix(private val exponent: Int) {
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

    fun prefixSymbol(locale: Locale?): String {
        return if (this == NOMINAL) "" else  getBundle(locale).getString(this.toString() + "_SYMBOL")
    }

    fun prefixName(locale: Locale?): String {
        return if (this == NOMINAL) "" else getBundle(locale).getString(this.toString())
    }

    fun getPrefixMultiplier(): BigDecimal {
        if (this == NOMINAL) return BigDecimal.ONE
        return if (exponent > 0) BigDecimal.TEN.pow(exponent)
        else BigDecimal.ONE.divide(BigDecimal.TEN.pow(exponent.absoluteValue))
    }

    private fun getBundle(locale: Locale? = null): ResourceBundle {
        val targetLocale = locale ?: Locale.getDefault()
        return ResourceBundle.getBundle(this::class.simpleName!!, targetLocale)
    }

    fun normalize(number: Number): BigDecimal {
        return BigDecimal(number.toString()).multiply(this.getPrefixMultiplier())
    }
}