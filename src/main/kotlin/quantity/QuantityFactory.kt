package quantity

import dimension.Dimension
import measurand.*
import unit.length.Meter
import unit.mass.Gram
import unit.time.Second
import java.math.BigDecimal
import kotlin.reflect.KClass

object QuantityFactory {
    private val quantityMap = hashMapOf<KClass<out AbstractQuantity<*>>, Dimension>()

    init {
        quantityMap[Length::class] = Dimension(Meter)
        quantityMap[Mass::class] = Dimension(Gram)
        quantityMap[Time::class] = Dimension(Second)
        quantityMap[Area::class] = Dimension(Meter, Meter)
        quantityMap[Volume::class] = Dimension(Meter, Meter, Meter)
    }

    fun dimensionFor(clazz: KClass<*>) = quantityMap[clazz] ?: throw Exception()

    fun getMeasurandByDimension(dimension: Dimension): KClass<*>? {
        for ((key, value) in quantityMap) {
            if (value == dimension) return key
        }
        return null
    }

    fun createInstanceOf(clazz: KClass<*>, value: BigDecimal): AbstractQuantity<*> {
        when (clazz) {
            Area::class -> return Area(value)
            Volume::class -> return Volume(value)
        }

        throw Exception()
    }
}
