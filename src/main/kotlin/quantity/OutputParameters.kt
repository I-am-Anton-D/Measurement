package quantity

import units.Prefix
import java.text.DecimalFormat
import java.util.*

class OutputParameters(
    var df: DecimalFormat = DecimalFormat(),
    var locale: Locale = Locale.getDefault(),
    var prefix: Prefix = Prefix.NOMINAL,
    var fullUnitName: Boolean = false
)
