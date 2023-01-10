package info.dmitrochenko.measurment.unit.mass


import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.dimension.Prefix.*
import info.dmitrochenko.measurment.abstracts.MetricUnit
import info.dmitrochenko.measurment.quantity.Mass
import info.dmitrochenko.measurment.quantity.massFrom

object Gram : MetricUnit<Mass>()

fun Number.gram() = massFrom(Gram.toDimension())
fun Number.gram(prefix: Prefix) = massFrom(Gram.prefix(prefix))

fun Number.ng()= gram(NANO)
