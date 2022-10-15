package quantity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import unit.MetricUnit
import util.Prefix
import java.math.BigDecimal
import java.util.*

internal class AbstractQuantityTest {

    object SomeUnit : MetricUnit<SomeQuantity>() {
        override fun symbol(locale: Locale): String {
            return "some units"
        }
    }

    object AnotherUnit:MetricUnit<SomeQuantity>(2) {
        override fun symbol(locale: Locale): String {
            return "another unit"
        }
    }

    class SomeQuantity(number: Number) : AbstractQuantity<SomeQuantity>(number, SomeUnit) {
        override fun copyWith(value: BigDecimal) = SomeQuantity(value)
    }

    fun Number.some() : SomeQuantity {
        return SomeQuantity(this)
    }


    @Test
    fun createInstancesTest() {
        assertDoesNotThrow {
            SomeQuantity(10)
        }

        assertDoesNotThrow {
            SomeQuantity(BigDecimal.TEN)
        }

        assertDoesNotThrow {
            5.some()
        }
    }

    @Test
    fun valueAndUnitTest() {
        var s = SomeQuantity(BigDecimal.ONE);

        assertThat(s.value).isEqualTo(BigDecimal.ONE)
        assertThat(s.unit).isEqualTo(SomeUnit)
        assertThat(s.unit).isNotEqualTo(AnotherUnit)

        s = SomeQuantity(1.2345)
        assertThat(s.value).isEqualTo(BigDecimal(1.2345.toString()))
    }

    @Test
    fun valueInTest() {
        val s = SomeQuantity(BigDecimal.TEN)
        assertThat(s to AnotherUnit).isEqualTo(BigDecimal(5))
    }

    @Test
    fun valueInPrefixTest() {
        val s = 1000.some()

        assertThat(s.valueIn(prefix = Prefix.KILO, unit = SomeUnit)).isEqualTo(BigDecimal.ONE)
        assertThat(s.valueIn(prefix = Prefix.KILO, unit = AnotherUnit)).isEqualTo(BigDecimal(0.5.toString()))
    }

    @Test
    fun infixMethodTest() {
        val result = 1000.some() to AnotherUnit

        assertThat(result).isInstanceOf(BigDecimal::class.java)
        assertThat(result).isEqualTo(BigDecimal(500))
    }


}