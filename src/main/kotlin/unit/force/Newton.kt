package unit.force

import dimension.Dimension
import quantity.Acceleration
import quantity.Force
import quantity.Mass
import java.util.*

object Newton : Dimension<Force>(Mass.kg(), Acceleration.baseDimension()) {
    override fun toString(locale: Locale) = "N"
}
