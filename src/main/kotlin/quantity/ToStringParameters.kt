package quantity

import dimension.Prefix
import unit.prototype.AbstractUnit
import java.text.DecimalFormat
import java.util.*

class ToStringParameters<Q>(
    var unit: AbstractUnit<Q>? = null,
    var prefix: Prefix? = null,
    var expand: Boolean = false,
    var normailze: Boolean = true,
    var df: DecimalFormat? = null,
    var locale: Locale = Locale.getDefault()
)

