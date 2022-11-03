package unit.prototype

import dimension.Dimension
import quantity.Quantity

class QuantityUnit(override val dimension: Dimension) : DimensionUnit<Quantity>()