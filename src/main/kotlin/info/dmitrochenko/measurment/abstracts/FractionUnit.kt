package info.dmitrochenko.measurment.abstracts

import java.math.BigDecimal

abstract class FractionUnit<Q>(ratio: Number = 1) : AbstractUnit<Q>(ratio) {
    val fractionMap = HashMap<BigDecimal, Fraction>()

    open fun getFractionString(value: BigDecimal): String? {
        return fractionMap[value]?.displayString
    }
}

//Maybe replace by simple string
class Fraction(val displayString: String)
