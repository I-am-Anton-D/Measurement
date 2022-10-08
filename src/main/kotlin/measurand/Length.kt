package measurand

import quantity.MetricQuantity
import units.AbstractUnit
import units.Meter
import units.Mile
import units.Prefix
import java.math.BigDecimal

class Length(number: Number) : MetricQuantity<Length>(number, Meter) {
    override fun copyWith(value: BigDecimal) = Length(value)
}

fun Number.meter(prefix: Prefix = Prefix.NOMINAL, unit: AbstractUnit<Length> = Meter): Length {
    val toMeterValue =
        if (unit == Meter) this else BigDecimal(this.toString()).multiply(BigDecimal(unit.ratio))
    val toPrefixValue = if (prefix == Prefix.NOMINAL) toMeterValue else prefix.normalize(toMeterValue)
    return Length(toPrefixValue)
}

fun Number.kilometer() = meter(Prefix.KILO)
fun Number.km() = kilometer()
fun Number.centimeter() = meter(Prefix.CENTI)
fun Number.cm() = centimeter()
fun Number.millimeter() = meter(Prefix.MILLI)
fun Number.mm() = millimeter()
fun Number.mile() = meter(unit = Mile)

fun Length.toMiles() = valueIn(unit = Mile)