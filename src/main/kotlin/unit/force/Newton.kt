package unit.force

import dimension.Dimension
import dimension.DimensionFormat
import measurand.Acceleration
import measurand.Force
import measurand.Mass
import java.util.*

object Newton : Dimension<Force>(Mass.kg(), Acceleration.baseDimension()) {

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
