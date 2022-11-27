package unit.length

import measurand.Length
import unit.prototype.AbstractUnit

object Mile : AbstractUnit<Length>(1609.344)

fun Number.mile() = Length(this, Mile)
fun Length.toMile() = valueIn(Mile)