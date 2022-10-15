package quantity

import units.AbstractUnit
import units.MetricUnit
import units.Prefix
import java.text.DecimalFormat
import java.util.*

class ToStringParameters<Q>(
    var df: DecimalFormat = DecimalFormat(),
    var locale: Locale = Locale.getDefault(),
    var expand: Boolean = false,
    var unit: AbstractUnit<Q>? = null
) {
    var prefix: Prefix = Prefix.NOMINAL

    constructor(
        df: DecimalFormat = DecimalFormat(),
        locale: Locale = Locale.getDefault(),
        prefix: Prefix = Prefix.NOMINAL,
        expand: Boolean = false,
        unit: MetricUnit<Q>
    ): this(df, locale, expand, unit) {
        this.prefix = prefix
    }
}