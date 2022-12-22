package unit.temperature

import quantity.Temperature
import quantity.tempIn
import unit.abstract.AbstractUnit
import java.math.BigDecimal

object Celsius : AbstractUnit<Temperature>(ratio = BigDecimal.ONE, zeroOffset = BigDecimal("273.15"))

fun Number.celsius() = tempIn(Celsius.toDimension())
fun Temperature.toCelsius() = valueIn(Celsius)