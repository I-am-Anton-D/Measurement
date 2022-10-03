package quantity

import units.AbstractUnit
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class AbstractQuantity<Q>(
    value: BigDecimal,
    unit: KClass<out AbstractUnit>
) {
    val value: BigDecimal = value
        get() = BigDecimal(field.toString())

    val unit: AbstractUnit = unit.createInstance()

    constructor(number: Number, baseUnit: KClass<out AbstractUnit>) : this(BigDecimal(number.toString()), baseUnit)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    open fun toString(outputParameters: OutputParameters) = "$value ${unit.toString(outputParameters, value)}"

    override fun toString() = "$value $unit"
}
