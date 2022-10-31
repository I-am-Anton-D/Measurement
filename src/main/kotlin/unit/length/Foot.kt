package unit.length

import measurand.Length

import unit.prototype.CompositeUnit
import unit.prototype.StreakUnit
import java.math.BigDecimal


object Foot : CompositeUnit<Length>(Inch, 12), StreakUnit {
    fun Number.foot() = meter(Foot)

    override fun getCompositeUnitString(valueIn: BigDecimal): String {
        val integerPart = valueIn.toBigInteger()
        val fractionPart = valueIn.remainder(BigDecimal.ONE)
        val intString = integerPart.toString() + symbol()
        fractionPart.multiply(parentRatio).meter(parentUnit)

        return ""
    }
}