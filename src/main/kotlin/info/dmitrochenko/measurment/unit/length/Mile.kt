package info.dmitrochenko.measurment.unit.length

import info.dmitrochenko.measurment.abstracts.AbstractUnit
import info.dmitrochenko.measurment.quantity.Length
import info.dmitrochenko.measurment.quantity.lengthIn

object Mile : AbstractUnit<Length>(1609.344)

fun Number.mile() = lengthIn(Mile.toDimension())
fun Length.toMile() = valueIn(Mile)