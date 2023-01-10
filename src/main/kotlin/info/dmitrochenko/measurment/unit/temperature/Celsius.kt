package info.dmitrochenko.measurment.unit.temperature


import info.dmitrochenko.measurment.abstracts.AbstractUnit
import info.dmitrochenko.measurment.quantity.Temperature
import info.dmitrochenko.measurment.quantity.tempIn
import java.math.BigDecimal

object Celsius : AbstractUnit<Temperature>(ratio = BigDecimal.ONE, zeroOffset = BigDecimal("273.15"))

fun Number.celsius() = tempIn(Celsius.toDimension())
fun Temperature.toCelsius() = valueIn(Celsius)