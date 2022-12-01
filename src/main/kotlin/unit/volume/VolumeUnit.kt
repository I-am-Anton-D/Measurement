package unit.volume

import dimension.Dimension
import measurand.Volume
import unit.length.Meter
import unit.prototype.DimensionUnit

object VolumeUnit : DimensionUnit<Volume>() {
    override val dimension = Dimension<Volume>(Meter, Meter, Meter)
}
