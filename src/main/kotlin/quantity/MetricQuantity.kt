package quantity

import units.AbstractUnit
import units.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass

abstract class MetricQuantity<Q>(number: Number, baseUnit: KClass<out AbstractUnit>) :
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

        val valueString = outputParameters.df.format(valueInPrefix)
        val prefixString = if (outputParameters.expand) prefix.prefixName(locale) else prefix.prefixSymbol(locale)
        val unitString = unit.toString(outputParameters, valueInPrefix)

        return "$valueString $prefixString$unitString"
    }

    open operator fun plus(other: AbstractQuantity<Q>): MetricQuantity<Q> {
        if (this.unit::class != other.unit::class) throw Exception()
        return copyWith(this.value + other.value) as MetricQuantity<Q>
    }
}