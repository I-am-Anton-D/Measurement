package unit.area

import measurand.Area
import unit.length.Meter
import unit.prototype.DimensionUnit

object AreaUnit : DimensionUnit<Area>() {
    override val dimension = Meter * Meter
}