package dimension

import unit.Prefix
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class UnitHolder(val unit: AbstractUnit<*>, val pow: Int = 1) {
    var prefix: Prefix = Prefix.NOMINAL
        private set

    val unitQuantity: Type = (unit.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]

    constructor(unit: MetricUnit<*>, pow: Int, prefix: Prefix) : this(unit, pow) {
        this.prefix = prefix
    }

    fun copyWith(pow: Int): UnitHolder = if (this.unit is MetricUnit) {
        UnitHolder(unit, pow, prefix)
    } else {
        UnitHolder(unit, pow)
    }

    fun inverse() = copyWith(-pow)

    fun canConvert(toUnit: UnitHolder) =
        pow == toUnit.pow && unitQuantity == toUnit.unitQuantity

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as UnitHolder

        return pow == other.pow && prefix == other.prefix && unit == other.unit && unitQuantity == other.unitQuantity
    }

    override fun hashCode(): Int {
        var result = unit.hashCode()
        result = 31 * result + pow
        result = 31 * result + prefix.hashCode()
        result = 31 * result + unitQuantity.hashCode()
        return result
    }
}