package unit.time

import measurand.Time
import unit.prototype.CompositeUnit

object Hour : CompositeUnit<Time>(Minute, 60)