package measurand

import quantity.AbstractQuantity
import unit.Inch
import unit.Meter
import unit.Mile
import java.math.BigDecimal

class Length(number: Number) : AbstractQuantity<Length>(number, Meter) {
    override fun copyWith(value: BigDecimal) = Length(value)
}

fun Length.toMile() = valueIn(Mile)
fun Length.toInch() = valueIn(Inch)