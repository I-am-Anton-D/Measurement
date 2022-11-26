package unit.force

import dimension.Dimension
import dimension.DimensionFormat
import measurand.Acceleration
import measurand.Force
import unit.Prefix
import unit.length.Meter
import unit.mass.Gram
import unit.mass.Kg
import java.util.*

object Newton : Dimension<Force>(Kg.toDimension(), Acceleration.baseDimension()) {

    override fun toString(dimensionFormat: DimensionFormat, locale: Locale) =
        when (dimensionFormat) {
            DimensionFormat.NORMAL -> toNormalFormatString(locale)
            DimensionFormat.ANSI -> toAnsiFormatString(locale)
        }

    override fun toAnsiFormatString(locale: Locale): String {
        return "N"
    }

    override fun toNormalFormatString(locale: Locale): String {
        return "N"
    }
}
