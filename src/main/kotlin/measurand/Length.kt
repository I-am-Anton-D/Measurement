package measurand

import quantity.AbstractQuantity
import unit.length.Inch
import unit.length.Meter
import unit.length.Mile
import java.math.BigDecimal

class Length(number: Number) : AbstractQuantity<Length>(number, Meter) {
    override fun copyWith(value: BigDecimal) = Length(value)
}

fun Length.toMile() = valueIn(Mile)
fun Length.toInch() = valueIn(Inch)