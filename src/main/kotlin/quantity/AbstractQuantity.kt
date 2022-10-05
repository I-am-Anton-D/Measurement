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

    open fun toString(outputParameters: OutputParameters) =
        "${outputParameters.df.format(value)} ${unit.toString(outputParameters, value)}"

    override fun toString() = "$value $unit"

    operator fun compareTo(other: AbstractQuantity<Q>): Int {
        return if (unit::class == other.unit::class) {
            this.value.compareTo(other.value)
        } else {
            throw Exception()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractQuantity<*>) return false

        return if (unit::class == other.unit::class) {
            this.value == other.value
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + unit.hashCode()
        return result
    }
}
