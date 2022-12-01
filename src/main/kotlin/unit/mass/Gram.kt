package unit.mass

import measurand.Mass
import measurand.massFrom
import dimension.Prefix
import dimension.Prefix.*
import unit.abstract.MetricUnit

object Gram : MetricUnit<Mass>()

fun Number.gram() = massFrom(Gram.toDimension())
fun Number.gram(prefix: Prefix) = massFrom(Gram.prefix(prefix))

fun Number.ng()= gram(NANO)
