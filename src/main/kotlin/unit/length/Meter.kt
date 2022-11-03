package unit.length

import dimension.Dimension
import measurand.Length
import unit.prototype.BaseUnit
import unit.prototype.MetricUnit

object Meter : BaseUnit<Length>(), MetricUnit {
    override val dimension = Dimension(Meter)
}