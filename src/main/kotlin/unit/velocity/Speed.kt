package unit.velocity

import measurand.Velocity
import unit.length.Meter
import unit.prototype.DimensionUnit
import unit.time.Second

object Speed : DimensionUnit<Velocity>() {
    override val dimension = Meter / Second
}
