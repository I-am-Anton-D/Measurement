package quantity

import units.MeasureUnit
import units.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class MetricQuantity<Q>(number: Number, baseUnit: KClass<out MeasureUnit>) :
    AbstractQuantity<Q>(number, baseUnit) {

    open infix fun valueIn(prefix: Prefix): BigDecimal {
        return value.divide(prefix.getPrefixMultiplier())
    }

    override fun toString(outputParameters: OutputParameters): String {
        val prefix = outputParameters.prefix
        val locale = outputParameters.locale

        if (prefix == Prefix.NOMINAL)
            return super.toString(outputParameters)

        val valueInPrefix = valueIn(prefix)

        val valueString = valueInPrefix.toString()
        val unitString = if (outputParameters.fullUnitName) prefix.prefixName(locale) + unit.createInstance().fullUnitName(locale, valueInPrefix)
            else prefix.prefixSymbol(locale) + unit.createInstance().unitSymbol(locale)

        return "$valueString $unitString"
    }

    open operator fun plus(other: AbstractQuantity<Q>): MetricQuantity<Q> {
        if (this.unit != other.unit) throw Exception()
        return copyWith(this.value + other.value) as MetricQuantity<Q>
    }
}