package quantity

import units.AbstractUnit
import java.math.BigDecimal
import java.math.MathContext

abstract class AbstractQuantity<Q>(val value: BigDecimal, val unit: AbstractUnit<Q>) {

    constructor(number: Number, unit: AbstractUnit<Q>) : this(BigDecimal(number.toString()), unit)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    open infix fun valueIn(unit: AbstractUnit<Q>): BigDecimal {
        return BigDecimal(value.toString()).divide(BigDecimal(unit.ratio), MathContext.DECIMAL128)
    }

    open fun toString(outputParameters: OutputParameters<Q>) =
        "${outputParameters.df.format(value)} ${unit.toString(outputParameters, value)}"

    override fun toString() = "$value $unit"

    @Suppress("UNCHECKED_CAST")
    open operator fun plus(other: AbstractQuantity<Q>): Q {
        if (this.unit != other.unit) throw Exception()
        return copyWith(this.value + other.value) as Q
    }

    operator fun compareTo(other: AbstractQuantity<Q>): Int {
        return if (unit == other.unit) {
            this.value.compareTo(other.value)
        } else {
            throw Exception()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractQuantity<*>) return false

        return if (unit == other.unit) {
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
