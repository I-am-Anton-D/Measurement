package quantity

import units.MeasureUnit
import java.math.BigDecimal
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class AbstractQuantity<Q>(
    value: BigDecimal,
    private val unit: KClass<out MeasureUnit>
) {
    val value: BigDecimal = value
        get() = BigDecimal(field.toString())

    constructor(number: Number, baseUnit: KClass<out MeasureUnit>) : this(BigDecimal(number.toString()), baseUnit)

    abstract fun copyWith(value: BigDecimal): AbstractQuantity<Q>

    override fun toString() = "${valueToString()} ${unitToString()}"

    fun toString(locale: Locale? = null) = "${valueToString()} ${unitToString(locale)}"

    open fun toStringWithFullUnitName(locale: Locale? = null) = "${valueToString()} ${unitToFullNameString(locale)}"

    open fun unitToFullNameString(locale: Locale? = null) = unit.createInstance().fullUnitName(locale)

    open fun unitToString(locale: Locale? = null) = unit.createInstance().unitSymbol(locale)

    open fun valueToString() = value.toString()

    operator fun plus(other: AbstractQuantity<Q>): AbstractQuantity<Q> {
        if (this.unit != other.unit) throw Exception()
        return copyWith(this.value + other.value)
    }
}