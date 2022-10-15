package measurand

import quantity.AbstractQuantity
import unit.*
import util.Prefix
import java.math.BigDecimal
import util.Prefix.*

class Length(number: Number) : AbstractQuantity<Length>(number, Meter) {
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