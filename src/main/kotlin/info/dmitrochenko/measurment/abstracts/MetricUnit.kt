package info.dmitrochenko.measurment.abstracts

import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.dimension.UnitHolder

abstract class MetricUnit<Q> : AbstractUnit<Q>() {
    open fun pow(pow: Int = 1, prefix: Prefix) = Dimension<Q>(UnitHolder(this, pow, prefix))
    open fun prefix(prefix: Prefix) =  Dimension<Q>(UnitHolder(this, 1, prefix))
 }