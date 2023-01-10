package info.dmitrochenko.measurment.unit.length


import info.dmitrochenko.measurment.abstracts.CompositeUnit
import info.dmitrochenko.measurment.quantity.Length
import info.dmitrochenko.measurment.quantity.lengthIn

object Foot : CompositeUnit<Length>(Inch, 12)

fun Number.foot() = lengthIn(Foot.toDimension())
fun Length.toFoot() = valueIn(Foot)