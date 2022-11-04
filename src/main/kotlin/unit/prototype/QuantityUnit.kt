package unit.prototype

import dimension.Dimension
import quantity.Quantity

class QuantityUnit(override val dimension: Dimension) : DimensionUnit<Quantity>() {
    override fun toString(): String {
        return "QuantityUnit(dimension=$dimension)"
    }
}



