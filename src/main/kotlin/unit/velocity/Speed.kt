package unit.velocity

import dimension.Dimension
import measurand.Velocity
import unit.length.Meter
import unit.prototype.DimensionUnit
import unit.time.Second

object Speed : DimensionUnit<Velocity>() {
    override val dimension = Dimension<Velocity>(Meter.pow(1), Second.pow(-1))
}
