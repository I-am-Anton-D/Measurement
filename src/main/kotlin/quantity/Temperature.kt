package quantity

import dimension.Dimension
import dimension.Prefix
import unit.temperature.Kelvin
import java.math.BigDecimal

class Temperature(number: Number) : AbstractQuantity<Temperature>(number, Kelvin) {

    constructor(number: Number, toStringDimension: Dimension<Temperature>) : this(number) {
        this.toStringDimension = toStringDimension
    }

    override fun copyWith(value: BigDecimal): AbstractQuantity<Temperature> = Temperature(value, toStringDimension)
}

fun Number.kelvin(prefix: Prefix = Prefix.NOMINAL) = Temperature(prefix.inNominal(this), Kelvin.prefix(prefix))
//or AbstractUnit<Temperature>
fun Number.tempIn(dimension:Dimension<Temperature>) = Temperature(dimension.convertValue( this, Kelvin), dimension)