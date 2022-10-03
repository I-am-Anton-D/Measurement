package quantity

import units.MeasureUnit
import java.math.BigDecimal
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class AbstractQuantity<Q>(
    value: BigDecimal,
    val unit: KClass<out MeasureUnit>
) {
    val value: BigDecimal = value
        get() = BigDecimal(field.toString())

    constructor(number: Number, baseUnit: KClass<out MeasureUnit>) : this(BigDecimal(number.toString()), baseUnit)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    override fun toString() = "${valueToString()} ${unitToString()}"

    open fun toString(outputParameters: OutputParameters): String {
        return valueToString() + " " + if (outputParameters.fullUnitName) unit.createInstance()
            .fullUnitName(outputParameters.locale, value) else unit.createInstance().unitSymbol(outputParameters.locale)
    }

    open fun unitToString(locale: Locale? = null) = unit.createInstance().unitSymbol(locale)

    open fun valueToString() = value.toString()
}
