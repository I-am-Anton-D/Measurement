package dimension

import java.math.BigDecimal

class ValueHolder<Q>(number: Number, val dimension: Dimension<Q>) {
    val value = BigDecimal(number.toString())

    fun convertValue(target: Dimension<Q>) = dimension.convertValue(target, value)
}