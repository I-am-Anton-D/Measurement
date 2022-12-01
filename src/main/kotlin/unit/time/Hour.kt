package unit.time

import quantity.Time
import unit.abstract.CompositeUnit

object Hour : CompositeUnit<Time>(Minute, 60)