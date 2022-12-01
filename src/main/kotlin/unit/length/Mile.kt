package unit.length

import quantity.Length
import quantity.lengthIn
import unit.abstract.AbstractUnit

object Mile : AbstractUnit<Length>(1609.344)

fun Number.mile() = lengthIn(Mile.toDimension())
fun Length.toMile() = valueIn(Mile)