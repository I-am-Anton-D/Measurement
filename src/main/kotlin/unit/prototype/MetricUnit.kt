package unit.prototype

import dimension.Dimension
import unit.Prefix

abstract class MetricUnit<Q> : AbstractUnit<Q>() {
    fun pow(pow: Int = 1, prefix: Prefix) = Dimension(this, pow, prefix)
    fun prefix(prefix: Prefix) =  Dimension(this, 1, prefix)
 }