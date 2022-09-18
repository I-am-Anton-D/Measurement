package quantity

import units.MeasureUnit
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class AbstractQuantity<Q>(
    value: BigDecimal,
    val baseUnit: KClass<out MeasureUnit>
) {
    val value: BigDecimal = value
        get() {
            return BigDecimal(field.toString())
        }

    constructor(number: Number, baseUnit: KClass<out MeasureUnit>) : this(BigDecimal(number.toString()), baseUnit)

    abstract fun copyWith(value: BigDecimal): Quantity<Q>

    override fun toString(): String {
        return value.toString() + " " + baseUnit.createInstance().unitSymbol()
    }

    fun toString(fullUnitName: Boolean): String {
        val unitInstance = baseUnit.createInstance()
        val valueString = value.toString()
        val unitString = if (fullUnitName) unitInstance.fullUnitName() else unitInstance.unitSymbol()

        return "$valueString $unitString"
    }

    operator fun plus(other: Quantity<Q>): Quantity<Q> {
        if (this.baseUnit != other.baseUnit) throw Exception()
        return copyWith(this.value + other.value)
    }
}