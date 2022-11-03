package unit.prototype

import dimension.Dimension

abstract class DimensionUnit<Q> : AbstractUnit<Q>() {
    abstract val dimension: Dimension
}