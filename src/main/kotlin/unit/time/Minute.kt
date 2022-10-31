package unit.time

import measurand.Time
import unit.prototype.CompositeUnit
import java.math.BigDecimal

object Minute : CompositeUnit<Time>(Second, 60) {

    override fun getCompositeUnitString(valueIn: BigDecimal): String {
        TODO("Not yet implemented")
    }
}