package unit.time

import measurand.Time
import unit.prototype.CompositeUnit

object Minute : CompositeUnit<Time>(Second, 60)