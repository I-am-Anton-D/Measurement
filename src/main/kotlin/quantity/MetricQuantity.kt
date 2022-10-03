package quantity

import units.MeasureUnit
import units.Prefix
import java.math.BigDecimal
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class MetricQuantity<Q>(number: Number, baseUnit: KClass<out MeasureUnit>) :
    AbstractQuantity<Q>(number, baseUnit) {

    open infix fun valueIn(prefix: Prefix): BigDecimal {
        return value.divide(prefix.getPrefixMultiplier())
    }

    fun toString(locale: Locale? = null, prefix: Prefix = Prefix.NOMINAL): String {
        if (prefix == Prefix.NOMINAL) return toString(locale)
        return valueIn(prefix).toString() + " " + prefix.prefixSymbol(locale) + unit.createInstance().unitSymbol(locale)
    }

    fun toStringWithFullUnitName(locale: Locale? = null, prefix: Prefix  = Prefix.NOMINAL) : String {
        return valueIn(prefix).toString() + " " + prefix.prefixName(locale) + unit.createInstance().pluralForm(locale)
    }

    open operator fun plus(other: AbstractQuantity<Q>): MetricQuantity<Q> {
        if (this.unit != other.unit) throw Exception()
        return copyWith(this.value + other.value) as MetricQuantity<Q>
    }
}