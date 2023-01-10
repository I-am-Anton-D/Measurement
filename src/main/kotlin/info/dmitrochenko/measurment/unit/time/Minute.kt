package info.dmitrochenko.measurment.unit.time


import info.dmitrochenko.measurment.abstracts.CompositeUnit
import info.dmitrochenko.measurment.quantity.Time

object Minute : CompositeUnit<Time>(Second, 60)