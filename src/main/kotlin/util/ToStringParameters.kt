package util

import exception.IllegalMetricPrefix
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit
import java.text.DecimalFormat
import java.util.*

class ToStringParameters<Q>(
    var df: DecimalFormat? = null,
    var locale: Locale = Locale.getDefault(),
    var expand: Boolean = false,
    unit: AbstractUnit<Q>? = null
) {
    var prefix: Prefix = Prefix.NOMINAL
        set(value) {
            if (unit !is MetricUnit && value != Prefix.NOMINAL) {
                throw IllegalMetricPrefix()
            }
            field = value
        }
    var unit: AbstractUnit<Q>? = unit
        set(value) {
            if (value !is MetricUnit) {
                this.prefix = Prefix.NOMINAL
            }
            field = value
        }

    constructor(
        df: DecimalFormat? = null,
        locale: Locale = Locale.getDefault(),
        prefix: Prefix = Prefix.NOMINAL,
        expand: Boolean = false,
        unit: MetricUnit<Q>
    ): this(df, locale, expand, unit) {
        this.prefix = prefix
    }
}