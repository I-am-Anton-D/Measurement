package unit.length

import measurand.Length
import measurand.meter
import unit.prototype.AbstractUnit

object Mile : AbstractUnit<Length>(1609.344)

fun Number.mile() = meter(Mile)
fun Length.toMile() = valueIn(Mile)