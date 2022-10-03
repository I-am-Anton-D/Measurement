package quantity

import units.Prefix
import java.util.*

class OutputParameters(
    var locale: Locale? = null,
    var prefix: Prefix = Prefix.NOMINAL,
    var fullNameUnit: Boolean = false
)
