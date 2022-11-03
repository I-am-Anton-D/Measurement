package unit.area

import dimension.Dimension
import measurand.Area
import unit.length.Meter
import unit.prototype.BaseUnit

object AreaUnit : BaseUnit<Area>() {
    override val dimension = Dimension(Meter, Meter)
}