package units

import java.math.BigDecimal
import kotlin.reflect.KClass

class Mile : AbstractUnit() {
    private val RATIO = 1609.344

    override fun convertTo(kClass: KClass<out AbstractUnit>, number: Number,): BigDecimal {
        if (kClass == Meter::class) {
            return BigDecimal(number.toString()).multiply(BigDecimal("1609.344"))
        }
        throw Exception()
    }

    override fun convertFrom(kClass: KClass<out AbstractUnit>, number: Number): BigDecimal {
        if (kClass == Meter::class) {
            return BigDecimal(number.toString()).divide(BigDecimal(RATIO.toString()))
        }
        throw Exception()
    }
}