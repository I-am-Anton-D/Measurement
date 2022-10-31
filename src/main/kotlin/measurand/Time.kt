package measurand

import quantity.AbstractQuantity
import unit.time.Second
import java.math.BigDecimal

class Time(number: Number) : AbstractQuantity<Time>(number, Second) {
    override fun copyWith(value: BigDecimal): AbstractQuantity<Time> {
        return Time(value)
    }
}