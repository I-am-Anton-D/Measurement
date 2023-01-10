package info.dmitrochenko.measurment.abstracts

import info.dmitrochenko.measurment.extension.toBigDecimal
import java.math.BigDecimal

abstract class CompositeUnit<Q>(val parentUnit: AbstractUnit<Q>, parentRatio: BigDecimal) :
    AbstractUnit<Q>(parentUnit.ratio * parentRatio) {

    constructor(parentUnit: AbstractUnit<Q>, parentRatio: Number) : this(parentUnit, parentRatio.toBigDecimal())

}