package measurand

import quantity.MetricQuantity
import quantity.Quantity
import units.Meter
import units.Prefix
import java.math.BigDecimal

class Length(number: Number) : MetricQuantity<Length>(number = number, baseUnit = Meter::class) {
    override fun copyWith(value: BigDecimal): Length {
        return Length(value)
    }
}

fun Number.meter(prefix: Prefix = Prefix.NOMINAL) : Length {
    val normalizedValue = prefix.normalize(this)
    return Length(normalizedValue)
}

fun Number.kilometer() = meter(Prefix.KILO)
fun Number.km() = kilometer()
fun Number.centimeter() = meter(Prefix.CENTI)
fun Number.cm() = centimeter()