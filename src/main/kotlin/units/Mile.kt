package units

import java.math.BigDecimal
import kotlin.reflect.KClass

class Mile:AbstractUnit() {

    override fun convertTo(number: Number, kClass: KClass<out AbstractUnit>): BigDecimal {
        if (kClass == Meter::class) {
            return BigDecimal(number.toString()).multiply(BigDecimal("1609.344"))
        }
        throw Exception()
    }
}