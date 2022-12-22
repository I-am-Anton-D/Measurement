package unit.abstract

import dimension.Dimension
import dimension.Prefix
import dimension.UnitHolder

abstract class MetricUnit<Q> : AbstractUnit<Q>() {
    open fun pow(pow: Int = 1, prefix: Prefix) = Dimension<Q>(UnitHolder(this, pow, prefix))
    open fun prefix(prefix: Prefix) =  Dimension<Q>(UnitHolder(this, 1, prefix))
 }