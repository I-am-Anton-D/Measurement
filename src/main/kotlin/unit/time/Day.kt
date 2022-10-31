package unit.time

import measurand.Time
import unit.prototype.CompositeUnit
import java.math.BigDecimal

object Day : CompositeUnit<Time>(Hour, 24) {
    override fun getCompositeUnitString(valueIn: BigDecimal): String {
        TODO("Not yet implemented")
    }
}