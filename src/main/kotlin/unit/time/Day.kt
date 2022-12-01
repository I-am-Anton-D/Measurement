package unit.time

import quantity.Time
import unit.abstract.CompositeUnit


object Day : CompositeUnit<Time>(Hour, 24)