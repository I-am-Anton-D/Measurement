package unit.length

import measurand.Length
import measurand.meter

import unit.prototype.CompositeUnit
import unit.prototype.StreakUnit

object Foot : CompositeUnit<Length>(Inch, 12), StreakUnit

fun Number.foot() = meter(Foot)
fun Length.toFoot() = valueIn(Foot)