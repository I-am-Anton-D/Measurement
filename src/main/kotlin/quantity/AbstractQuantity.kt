package quantity

import units.MeasureUnit
import units.Prefix
import java.math.BigDecimal
import java.util.*
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

    open infix fun valueIn(prefix: Prefix): BigDecimal {
        return value.divide(prefix.getPrefixMultiplier())
    }

    open infix fun valueIn(units: MeasureUnit): BigDecimal {
        //return this.value.multiply(prefix.getPrefixMultiplier())
        return BigDecimal.ONE
    }

    override fun toString(): String {
        return value.toString() + " " + baseUnit.createInstance().unitSymbol()
    }

    fun toString(locale: Locale? = null, unitFullName: Boolean = false, prefix: Prefix = Prefix.NOMINAL): String {
        val unitInstance = baseUnit.createInstance()
        val valueString = (this valueIn prefix).toString()

        val unitString = if (unitFullName) {
            if (value == BigDecimal.ONE) {
                unitInstance.fullUnitName(locale)
            } else {
                unitInstance.pluralForm(locale)
            }
        } else {
            unitInstance.unitSymbol(locale)
        }

        return "$valueString $unitString"
    }

    operator fun plus(other: Quantity<Q>): Quantity<Q> {
        if (this.baseUnit != other.baseUnit) throw Exception()
        return copyWith(this.value + other.value)
    }

    operator fun plus(number: Number) : Quantity<Q> {
        return copyWith(this.value + BigDecimal(number.toString()))
    }
}