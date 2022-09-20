package quantity

import units.MeasureUnit
import units.Prefix
import java.math.BigDecimal
import kotlin.reflect.KClass

open class MetricQuantity<Q>(number: Number, baseUnit: KClass<out MeasureUnit>) : AbstractQuantity<Q>(number, baseUnit) {

    override fun copyWith(value: BigDecimal): AbstractQuantity<Q> {
        return MetricQuantity(value, this.baseUnit)
    }

    open infix fun valueIn(prefix: Prefix): BigDecimal {
        return value.divide(prefix.getPrefixMultiplier())
    }
}