package quantity

import units.MeasureUnit
import java.math.BigDecimal
import kotlin.reflect.KClass

open class Quantity<Q>(number: Number, baseUnit: KClass<out MeasureUnit>) : AbstractQuantity<Q>(number, baseUnit){

    override fun copyWith(value:BigDecimal) : Quantity<Q> {
        return Quantity(value, this.baseUnit)
    }
}