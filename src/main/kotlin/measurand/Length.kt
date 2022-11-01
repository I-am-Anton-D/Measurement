package measurand

import quantity.AbstractQuantity
import unit.length.Inch
import unit.length.Meter
import unit.length.Mile
import unit.prototype.AbstractUnit
import unit.prototype.Prefix
import java.math.BigDecimal

class Length(number: Number, useUnits: AbstractUnit<Length>? = null, usePrefix: Prefix? = null) :
    AbstractQuantity<Length>(number, Meter, useUnits, usePrefix) {

    override fun copyWith(value: BigDecimal) = Length(value, useUnit, usePrefix)
}

fun Length.toMile() = valueIn(Mile)
fun Length.toInch() = valueIn(Inch)