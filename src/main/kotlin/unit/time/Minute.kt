package unit.time

import measurand.Time
import unit.abstract.CompositeUnit

object Minute : CompositeUnit<Time>(Second, 60)