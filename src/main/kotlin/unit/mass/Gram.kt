package unit.mass

import quantity.Mass
import quantity.massFrom
import dimension.Prefix
import dimension.Prefix.*
import unit.abstract.MetricUnit

object Gram : MetricUnit<Mass>()

fun Number.gram() = massFrom(Gram.toDimension())
fun Number.gram(prefix: Prefix) = massFrom(Gram.prefix(prefix))

fun Number.ng()= gram(NANO)
