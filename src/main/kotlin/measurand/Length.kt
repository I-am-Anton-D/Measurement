package measurand

import quantity.MetricQuantity
import units.*
import java.math.BigDecimal
import units.Prefix.*

class Length(number: Number) : MetricQuantity<Length>(number, Meter) {
    override fun copyWith(value: BigDecimal) = Length(value)
}

fun Number.meter(prefix: Prefix = NOMINAL, unit: MetricUnit<Length> = Meter) =
    Length(unit.valueInBaseUnit(prefix.getNominalValue(this)))

fun Number.meter(unit: AbstractUnit<Length>) =
    Length(unit.valueInBaseUnit(this))


fun Number.kilometer() = meter(KILO)
fun Number.km() = kilometer()
fun Number.centimeter() = meter(CENTI)
fun Number.cm() = centimeter()
fun Number.millimeter() = meter(MILLI)
fun Number.mm() = millimeter()
fun Number.mile() = meter(Mile)

fun Length.toMiles() = valueIn(Mile)