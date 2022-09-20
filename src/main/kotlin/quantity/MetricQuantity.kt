package quantity

import units.MeasureUnit
import units.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass

abstract class MetricQuantity<Q>(number: Number, baseUnit: KClass<out MeasureUnit>) : AbstractQuantity<Q>(number, baseUnit) {

    open infix fun valueIn(prefix: Prefix): BigDecimal {
        return value.divide(prefix.getPrefixMultiplier())
    }
}