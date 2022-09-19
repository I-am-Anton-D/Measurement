package measurand

import quantity.Quantity
import units.Meter
import java.math.BigDecimal

class Length(number: Number) : Quantity<Length>(number = number, baseUnit = Meter::class) {
    override fun copyWith(value: BigDecimal): Quantity<Length> {
        return Length(value)
    }
}

fun Number.meter(): Length {
    return Length(this)
}

fun Number.kilometer(): Length {
    val normalizedValue = BigDecimal(this.toString()).multiply(BigDecimal(1000))
    return Length(normalizedValue)
}