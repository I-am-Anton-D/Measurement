package quantity

import units.AbstractUnit
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class AbstractQuantity<Q>(
    value: BigDecimal,
    val unit: KClass<out AbstractUnit>
) {
    val value: BigDecimal = value
        get() = BigDecimal(field.toString())

    constructor(number: Number, baseUnit: KClass<out AbstractUnit>) : this(BigDecimal(number.toString()), baseUnit)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    override fun toString() = "$value ${unit.createInstance()}"

    open fun toString(outputParameters: OutputParameters) = "$value ${unit.createInstance().toString(outputParameters, value)}"
}
