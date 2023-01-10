package info.dmitrochenko.measurment.unit.force

import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.quantity.Force
import info.dmitrochenko.measurment.quantity.Mass

import java.util.*

object Newton : Dimension<Force>(Mass.kg(), info.dmitrochenko.measurment.quantity.Acceleration.baseDimension()) {
    override fun toString(locale: Locale) = "N"
}
