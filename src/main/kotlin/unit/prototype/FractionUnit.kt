package unit.prototype

import java.math.BigDecimal

open class FractionUnit<Q>(ratio: Number = 1) : AbstractUnit<Q>(ratio) {
    protected val fractionMap = HashMap<BigDecimal, Fraction>()

    open fun getFractionString(value: BigDecimal): String {
        return fractionMap[value]?.displayString ?: ""
    }
}

//Maybe replace by simple string
class Fraction(val displayString: String)
