package dimension

import quantity.Quantity
import unit.Prefix
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class UnitHolder(val unit: AbstractUnit<*>, var pow: Int = 1) {
    var prefix: Prefix = Prefix.NOMINAL
        private set

    val unitQuantity: Type? = (unit.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]

    constructor(unit: MetricUnit<*>, pow: Int, prefix: Prefix) : this(unit, pow) {
        this.prefix = prefix
    }

    fun inverse(): UnitHolder {
        return if (this.unit is MetricUnit) {
            UnitHolder(unit, -pow, prefix)
        } else {
            UnitHolder(unit, -pow)
        }
    }

    operator fun times(other: AbstractUnit<*>) = Dimension<Quantity>(this, UnitHolder(other))

    operator fun times(other: UnitHolder) = Dimension<Quantity>(this, other)

    operator fun div(other: AbstractUnit<*>) = Dimension<Quantity>(this, UnitHolder(other, -1))

    operator fun div(other: UnitHolder): Dimension<Quantity> = Dimension(this, other.inverse())

    fun canConvert(toUnit: UnitHolder) =
        pow == toUnit.pow && unitQuantity == toUnit.unitQuantity

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as UnitHolder

        return pow == other.pow && prefix == other.prefix && unit == other.unit && unitQuantity == other.unitQuantity
    }

    override fun hashCode(): Int {
        return unit.hashCode()
    }
}