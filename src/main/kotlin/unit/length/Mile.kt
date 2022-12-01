package unit.length

import measurand.Length
import measurand.lengthFrom
import unit.prototype.AbstractUnit

object Mile : AbstractUnit<Length>(1609.344)

fun Number.mile() = lengthFrom(Mile.toDimension())
fun Length.toMile() = valueIn(Mile)