package measurand

import quantity.MetricQuantity
import units.AbstractUnit
import units.Meter
import units.Mile
import units.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class Length(number: Number) : MetricQuantity<Length>(number, Meter::class) {
       override fun copyWith(value: BigDecimal) = Length(value)
}

fun Number.meter(prefix: Prefix = Prefix.NOMINAL, unit: KClass<out AbstractUnit> = Meter::class) : Length {
    val toMeterValue = if (unit == Meter::class) this else unit.createInstance().convertTo(Meter::class, this)
    val normalizedValue = if (prefix == Prefix.NOMINAL) toMeterValue else prefix.normalize(toMeterValue)
    return Length(normalizedValue)
}

fun Number.kilometer() = meter(Prefix.KILO)
fun Number.km() = kilometer()
fun Number.centimeter() = meter(Prefix.CENTI)
fun Number.cm() = centimeter()
fun Number.millimeter() = meter(Prefix.MILLI)
fun Number.mm() = millimeter()
fun Number.mile() = meter(unit = Mile::class)