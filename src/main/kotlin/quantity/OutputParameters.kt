package quantity

import units.AbstractUnit
import units.Prefix
import java.text.DecimalFormat
import java.util.*

class OutputParameters<Q>(
    var df: DecimalFormat = DecimalFormat(),
    var locale: Locale = Locale.getDefault(),
    var prefix: Prefix = Prefix.NOMINAL,
    var expand: Boolean = false,
    var unit: AbstractUnit<Q>? = null
)
