package unit.time

import measurand.Time
import unit.prototype.CompositeUnit
import java.math.BigDecimal

object Hour : CompositeUnit<Time>(Minute, 60) {
    override fun getCompositeUnitString(valueIn: BigDecimal): String {
        TODO("Not yet implemented")
    }
}