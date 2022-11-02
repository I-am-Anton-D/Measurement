package quantity

import unit.prototype.AbstractUnit
import unit.prototype.Prefix
import java.text.DecimalFormat
import java.util.*

class ToStringParameters<Q>(
    var unit: AbstractUnit<Q>? = null,
    var prefix: Prefix? = null,
    var expand: Boolean = false,
    var normailze: Boolean = true,
    var df: DecimalFormat? = null,
    var locale: Locale = Locale.getDefault()
) {
    fun copy() = ToStringParameters(unit, prefix, expand, normailze, df, locale)
}


