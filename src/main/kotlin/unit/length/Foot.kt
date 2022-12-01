package unit.length

import measurand.Length
import measurand.lengthFrom

import unit.prototype.CompositeUnit
import unit.prototype.StreakUnit

object Foot : CompositeUnit<Length>(Inch, 12), StreakUnit

fun Number.foot() = lengthFrom(Foot.toDimension())
fun Length.toFoot() = valueIn(Foot)