package info.dmitrochenko.measurment.dimension

import info.dmitrochenko.measurment.abstracts.AbstractUnit
import info.dmitrochenko.measurment.abstracts.MetricUnit
import info.dmitrochenko.measurment.exception.ConvertUnitException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.absoluteValue

class UnitHolder {
    val unit: AbstractUnit<*>
    val pow: Int
    val prefix: Prefix
    val unitQuantity: Type

    constructor(unit: AbstractUnit<*>, pow: Int = 1)  {
        this.prefix = Prefix.NOMINAL
        this.unit = unit
        this.pow = pow
        this.unitQuantity = (unit.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
    }

    constructor(unit: MetricUnit<*>, pow: Int = 1, prefix: Prefix = Prefix.NOMINAL)  {
        this.prefix = prefix
        this.unit = unit
        this.pow = pow
        this.unitQuantity = (unit.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
    }

    fun copyWith(pow: Int) = if (unit is MetricUnit) UnitHolder(unit, pow, prefix) else UnitHolder(unit, pow)

    fun invert() = copyWith(-pow)

    fun canConvert(toUnit: UnitHolder) = pow == toUnit.pow && unitQuantity == toUnit.unitQuantity

    fun calculateConvertRatio(toUnit: UnitHolder): BigDecimal {
        if (!canConvert(toUnit)) throw ConvertUnitException("Can not convert $this to $toUnit")

        val ratio = unit.ratio.divide(toUnit.unit.ratio, MathContext.DECIMAL128)
        val prefixMultiplier = prefix.multiplier().divide(toUnit.prefix.multiplier(), MathContext.DECIMAL128)
        return ratio.multiply(prefixMultiplier).pow(toUnit.pow.absoluteValue)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return other is UnitHolder && pow == other.pow && prefix == other.prefix && unit == other.unit
    }

    override fun hashCode(): Int {
        var result = unit.hashCode()
        result = 31 * result + pow
        result = 31 * result + prefix.hashCode()
        return result
    }
}