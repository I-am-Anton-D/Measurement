package measurand

import dimension.Dimension
import quantity.AbstractQuantity
import quantity.QuantityFactory
import java.math.BigDecimal

class Area(number: Number) : AbstractQuantity<Area>(BigDecimal(number.toString())) {
    override val dimension: Dimension = QuantityFactory.dimensionFor(this::class)

    override fun copyWith(value: BigDecimal): AbstractQuantity<Area> {
        return Area(value)
    }
}

fun Number.sqmeter(): Area {
    return Area(this)
}


fun Number.sqkm(): Area {
    return Area(this)
}

