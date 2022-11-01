package quantity

import unit.prototype.AbstractUnit
import unit.prototype.Prefix
import java.text.DecimalFormat
import java.util.*

class ToStringParameters<Q>(
    var df: DecimalFormat? = null,
    var locale: Locale = Locale.getDefault(),
    var expand: Boolean = false,
    var normailze: Boolean = true,
    var prefix: Prefix? = null,
    var unit: AbstractUnit<Q>? = null
)

