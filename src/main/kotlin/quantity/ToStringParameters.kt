package quantity

import unit.Prefix
import unit.prototype.AbstractUnit
import java.text.DecimalFormat
import java.util.*

class ToStringParameters<Q>(
    var unit: AbstractUnit<Q>? = null,
    var prefix: Prefix? = null,
    var expand: Boolean? = null,
    var normalize: Boolean? = null,
    var df: DecimalFormat? = null,
    var locale: Locale? = null
)

