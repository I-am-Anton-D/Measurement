package unit.prototype

import dimension.UnitHolder
import unit.Prefix

abstract class MetricUnit<Q> : AbstractUnit<Q>() {

    fun pow(pow: Int = 1, prefix: Prefix) = UnitHolder(this, pow, prefix)
    fun prefix(prefix: Prefix) = UnitHolder(this, 1, prefix)
 }