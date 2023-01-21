package info.dmitrochenko.measurment.quantity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.unit.temperature.Kelvin
import java.math.BigDecimal

class Temperature(number: Number) : AbstractQuantity<Temperature>(number, Kelvin) {

    constructor(number: Number, toStringDimension: Dimension<Temperature>) : this(number) {
        this.toStringDimension = toStringDimension
    }

    override fun copyWith(value: BigDecimal): AbstractQuantity<Temperature> = Temperature(value, toStringDimension)
}

fun Number.kelvin(prefix: Prefix = Prefix.NOMINAL) = Temperature(prefix.inNominal(this), Kelvin.prefix(prefix))
//or AbstractUnit<Temperature>
fun Number.tempIn(dimension: Dimension<Temperature>) = Temperature(dimension.convertValue( this, Kelvin), dimension)