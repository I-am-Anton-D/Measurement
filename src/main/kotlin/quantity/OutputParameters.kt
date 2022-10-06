package quantity

import units.AbstractUnit
import units.Prefix
import java.text.DecimalFormat
import java.util.*
import kotlin.reflect.KClass

class OutputParameters(
    var df: DecimalFormat = DecimalFormat(),
    var locale: Locale = Locale.getDefault(),
    var prefix: Prefix = Prefix.NOMINAL,
    var expand: Boolean = false,
    var unit: KClass<out AbstractUnit<*>>? = null
)
