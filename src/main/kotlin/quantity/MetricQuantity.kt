package quantity

import units.AbstractUnit
import units.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class MetricQuantity<Q>(number: Number, unit: KClass<out AbstractUnit>) :
    AbstractQuantity<Q>(number, unit) {

    open fun valueIn(prefix: Prefix = Prefix.NOMINAL, target: KClass<out AbstractUnit>? = unit::class): BigDecimal {
        val valueIn = if (target == unit::class || target == null) value else target.createInstance().convertFrom(unit::class, value)

        return if (prefix == Prefix.NOMINAL) {
            valueIn
        } else {
            valueIn.divide((prefix.getPrefixMultiplier()))
        }
    }

    override fun toString(outputParameters: OutputParameters): String {
        val prefix = outputParameters.prefix
        val locale = outputParameters.locale
        val targetUnit = outputParameters.unit ?: unit::class

        val valueIn = valueIn(prefix, targetUnit)

        val valueString = outputParameters.df.format(valueIn)
        val prefixString = if (outputParameters.expand) prefix.prefixName(locale) else prefix.prefixSymbol(locale)
        val unitString = targetUnit.createInstance().toString(outputParameters, valueIn)

        return "$valueString $prefixString$unitString"
    }
}