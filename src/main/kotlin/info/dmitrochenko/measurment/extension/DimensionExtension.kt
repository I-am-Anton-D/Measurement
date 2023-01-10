package info.dmitrochenko.measurment.extension

import info.dmitrochenko.measurment.dimension.Dimension
import java.util.*

fun <Q> Dimension<Q>.toAnsiString(locale: Locale)  : String {
        var ansiString = ""
        this.getUnitList().forEach { uh ->
            val prefix = uh.prefix.symbol(locale)
            val symbol = uh.unit.symbol(locale)
            val powString = if (uh.pow == 1) "" else "^${uh.pow}"
            val space = (if (ansiString.isNotEmpty()) " " else "")
            ansiString += space + prefix + symbol + powString
        }

        return ansiString
}