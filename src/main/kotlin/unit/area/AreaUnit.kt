package unit.area

import dimension.Dimension
import measurand.Area
import unit.length.Meter
import unit.prototype.DimensionUnit

object AreaUnit : DimensionUnit<Area>() {
    override val dimension = Dimension<Area>(Meter, Meter)
}