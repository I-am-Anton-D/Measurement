package unit.mass

import measurand.Mass
import measurand.massFrom
import unit.Prefix
import unit.Prefix.*
import unit.prototype.MetricUnit

object Gram : MetricUnit<Mass>()

fun Number.gram() = massFrom(Gram.toDimension())
fun Number.gram(prefix: Prefix) = massFrom(Gram.prefix(prefix))

fun Number.ng()= gram(NANO)
