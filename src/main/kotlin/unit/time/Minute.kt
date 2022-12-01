package unit.time

import quantity.Time
import unit.abstract.CompositeUnit

object Minute : CompositeUnit<Time>(Second, 60)