package unit.prototype
import java.math.BigDecimal

abstract class CompositeUnit<Q>(val parentUnit: AbstractUnit<Q>, val parentRatio: BigDecimal) :
    AbstractUnit<Q>(parentUnit.ratio * parentRatio) {

    constructor(parentUnit: AbstractUnit<Q>, parentRatio: Number) : this(
        parentUnit, BigDecimal(
            parentRatio.toString()
        )
    )
}