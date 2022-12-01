package unit.prototype

import dimension.Dimension
import dimension.UnitHolder
import unit.Prefix
import java.math.BigDecimal

abstract class MetricUnit<Q> : AbstractUnit<Q>() {

    open fun pow(pow: Int = 1, prefix: Prefix) = Dimension<Q>(UnitHolder(this, pow, prefix))

    open fun prefix(prefix: Prefix) =  Dimension<Q>(UnitHolder(this, 1, prefix))

    open fun valueToBaseUnit(number: Number, prefix: Prefix): BigDecimal = prefix.inNominal(number).multiply(ratio)
 }