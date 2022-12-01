package unit.length

import measurand.Length
import measurand.lengthIn

import unit.abstract.CompositeUnit

object Foot : CompositeUnit<Length>(Inch, 12)

fun Number.foot() = lengthIn(Foot.toDimension())
fun Length.toFoot() = valueIn(Foot)