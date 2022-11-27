package unit.prototype

import dimension.Dimension
import dimension.UnitHolder
import dimension.ValueHolder
import unit.Prefix
import java.math.BigDecimal

abstract class MetricUnit<Q> : AbstractUnit<Q>() {

    open fun pow(pow: Int = 1, prefix: Prefix) = Dimension(this, pow, prefix)

    open fun prefix(prefix: Prefix) = Dimension(this, 1, prefix)

    operator fun invoke(number: Number, prefix: Prefix) = ValueHolder(number, Dimension<Q>(UnitHolder(this, 1, prefix)))

    open fun valueToBaseUnit(number: Number, prefix: Prefix): BigDecimal = prefix.inNominal(number).multiply(ratio)
}