package measurand

import quantity.Quantity
import units.Meter
import units.Prefix
import java.math.BigDecimal

class Length(number: Number) : Quantity<Length>(number = number, baseUnit = Meter::class) {
    override fun copyWith(value: BigDecimal): Quantity<Length> {
        return Length(value)
    }
}

fun Number.meter(prefix: Prefix = Prefix.NOMINAL) : Length {
    val normalizedValue = prefix.normalize(this)
    return Length(normalizedValue)
}
fun Number.kilometer() = meter(Prefix.KILO)
fun Number.centimeter() = meter(Prefix.CENTI)