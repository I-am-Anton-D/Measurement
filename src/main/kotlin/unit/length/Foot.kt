package unit.length

import quantity.Length
import quantity.lengthIn

import unit.abstract.CompositeUnit

object Foot : CompositeUnit<Length>(Inch, 12)

fun Number.foot() = lengthIn(Foot.toDimension())
fun Length.toFoot() = valueIn(Foot)