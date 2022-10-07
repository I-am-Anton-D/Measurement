package quantity

import units.AbstractUnit
import java.math.BigDecimal
import java.math.MathContext
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class AbstractQuantity<Q>(
    val value: BigDecimal,
    val unit: AbstractUnit<Q>
) {
    constructor(number: Number, baseUnit: KClass<out AbstractUnit<Q>>) : this(
        BigDecimal(number.toString()),
        baseUnit.createInstance()
    )

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    open fun toString(outputParameters: OutputParameters) =
        "${outputParameters.df.format(value)} ${unit.toString(outputParameters, value)}"

    override fun toString() = "$value $unit"

    open infix fun valueIn(unit: KClass<out AbstractUnit<Q>>): BigDecimal {
        val ratio = unit.createInstance().ratio
        return BigDecimal(value.toString()).divide(BigDecimal(ratio), MathContext.DECIMAL128)
    }

    @Suppress("UNCHECKED_CAST")
    open operator fun plus(other: AbstractQuantity<Q>): Q {
        if (this.unit::class != other.unit::class) throw Exception()
        return copyWith(this.value + other.value) as Q
    }

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
