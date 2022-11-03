package quantity

import dimension.Dimension
import measurand.Area
import measurand.Length
import unit.length.Meter
import java.math.BigDecimal

object QuantityFactory {
    private val quantityMap = hashMapOf<String?, Dimension>()

    init {
        quantityMap[Length::class.simpleName] = Dimension(Meter)
//        quantityMap[Mass::class] = Dimension(Gram)
//        quantityMap[Time::class] = Dimension(Second)
        quantityMap[Area::class.simpleName] = Dimension(Meter, Meter)
    }

    fun dimensionFor(clazz: String) = quantityMap[clazz] ?: throw Exception()

    fun getMeasurandByDimension(dimension: Dimension): String? {
        for ((key, value) in quantityMap) {
            if (value == dimension) return key
        }
        return null
    }

    fun createInstanceOf(clazz: String, value: BigDecimal): AbstractQuantity<*> {
        when (clazz) {
            Area::class.simpleName -> return Area(value)
//            Volume::class -> return Volume(value)
        }

        throw Exception()
    }
}