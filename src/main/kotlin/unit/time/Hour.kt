package unit.time

import measurand.Time
import unit.abstract.CompositeUnit

object Hour : CompositeUnit<Time>(Minute, 60)