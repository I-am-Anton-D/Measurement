package unit.length

import measurand.Length
import measurand.lengthIn

import unit.prototype.CompositeUnit
import unit.prototype.StreakUnit

object Foot : CompositeUnit<Length>(Inch, 12), StreakUnit

fun Number.foot() = lengthIn(Foot.toDimension())
fun Length.toFoot() = valueIn(Foot)