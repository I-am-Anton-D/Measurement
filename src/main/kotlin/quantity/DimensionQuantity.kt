package quantity

import unit.prototype.DimensionUnit

abstract class DimensionQuantity<Q>(number: Number) : AbstractQuantity<Q>(number) {
    abstract override val baseUnit: DimensionUnit<Q>
}