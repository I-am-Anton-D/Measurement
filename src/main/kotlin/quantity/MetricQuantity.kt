package quantity

import units.AbstractUnit
import units.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class MetricQuantity<Q>(number: Number, unit: KClass<out AbstractUnit<Q>>) :
    AbstractQuantity<Q>(number, unit) {

    open fun valueIn(
        prefix: Prefix = Prefix.NOMINAL,
        unit: KClass<out AbstractUnit<Q>> = this.unit::class
    ): BigDecimal {
        return super.valueIn(unit).divide((prefix.getPrefixMultiplier()))
    }

    @Suppress("UNCHECKED_CAST")
    override fun toString(outputParameters: OutputParameters): String {
        val prefix = outputParameters.prefix
        val locale = outputParameters.locale
        val targetUnit = outputParameters.unit ?: unit::class
        val instanceOfUnit = targetUnit.createInstance()

        if (instanceOfUnit.measurand != this.unit.measurand) throw Exception()

        val valueIn = valueIn(prefix, targetUnit as KClass<AbstractUnit<Q>>)

        val valueString = outputParameters.df.format(valueIn)
        val prefixString = if (outputParameters.expand) prefix.prefixName(locale) else prefix.prefixSymbol(locale)
        val unitString = instanceOfUnit.toString(outputParameters, valueIn)

        return "$valueString $prefixString$unitString"
    }
}