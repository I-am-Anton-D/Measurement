package unit.temperature

import quantity.Temperature
import quantity.tempIn
import unit.abstract.AbstractUnit
import java.math.BigDecimal

val RATIO: BigDecimal = BigDecimal("0.5555555555")
val ZERO_OFFSET: BigDecimal =  BigDecimal("-459.67")

object Fahrenheit : AbstractUnit<Temperature>(
    ratio = RATIO,
    zeroOffset = ZERO_OFFSET
) {

    //Some hack..
    override fun resolveZeroOffset(fromUnit: AbstractUnit<*>, toUnit: AbstractUnit<*>): BigDecimal {
        return if (fromUnit == this) {
            -ZERO_OFFSET
        } else {
            ZERO_OFFSET * RATIO
        }
    }
}

fun Number.fahrenheit() = tempIn(Fahrenheit.toDimension())
fun Temperature.toFahrenheit() = valueIn(Fahrenheit)