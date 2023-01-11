package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import info.dmitrochenko.measurment.abstracts.AbstractUnit
import info.dmitrochenko.measurment.abstracts.MetricUnit
import java.math.BigDecimal
import java.util.*

@Suppress("KotlinConstantConditions")
internal class AbstractQuantityTest {

    object SomeUnit : MetricUnit<SomeQuantity>() {
        override fun symbol(locale: Locale): String {
            return "some symbol"
        }

        override fun name(locale: Locale): String {
            return "some singular"
        }
    }

    object AnotherUnit : MetricUnit<SomeQuantity>() {
        override fun symbol(locale: Locale): String {
            return "another symbol"
        }

        override fun name(locale: Locale): String {
            return "another singular"
        }

    }

    class SomeQuantity(number: Number) : AbstractQuantity<SomeQuantity>(number, SomeUnit) {
        override fun copyWith(value: BigDecimal) = SomeQuantity(value)
    }

    private fun Number.some(): SomeQuantity {
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
    fun minusTest() {
        val q1 = SomeQuantity(10)
        val q2 = SomeQuantity(BigDecimal.TEN)

        val sum = q1 - q2

        assertThat(sum).isInstanceOf(SomeQuantity::class.java)
        assertThat(sum.value).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun compareToTest() {
        val q1 = 10.some()
        val q2 = 20.some()

        assertThat(q2 > q1).isTrue
        assertThat(q2 < q1).isFalse
    }

    class AnotherQuantity(number: Number) : AbstractQuantity<SomeQuantity>(number, AnotherUnit) {
        override fun copyWith(value: BigDecimal): AbstractQuantity<SomeQuantity> {
            return AnotherQuantity(value)
        }
    }


    //TODO tempest solution, need to refactor
    @Test
    fun difUnitOnPlusException() {
        val q1 = SomeQuantity(10)
        val q2 = AnotherQuantity(10)

        assertThrows<Exception> {
            q1 + q2
        }
    }

    @Test
    fun difUnitOnMinusException() {
        val q1 = SomeQuantity(10)
        val q2 = AnotherQuantity(10)

        assertThrows<Exception> {
            q1 - q2
        }
    }

    @Test
    fun difUnitOnCompareException() {
        val q1 = SomeQuantity(10)
        val q2 = AnotherQuantity(10)

        assertThrows<Exception> {
            q1 > q2
        }
    }

    @Test
    fun toStringStrict() {
        val q1 = SomeQuantity(10)
        assertThat(q1.toString()).isEqualTo("10 " + SomeUnit.symbol())
    }

    @Test
    fun toStringWithParameter() {

    }

    object NotMetricUnit : AbstractUnit<SomeQuantity>() {
        override fun symbol(locale: Locale): String {
            return "not metric"
        }
    }

    @Test
    fun toStringWithQParamsAndNullUnits() {
        val q1 = 10.some()


        assertThat(q1.toString(unit = NotMetricUnit)).isEqualTo("10 " + NotMetricUnit.symbol())
    }

    @Test
    fun equalsAndHashCodeTest() {
        val q1 = SomeQuantity(10)
        val q2 = SomeQuantity(10)
        val q3 = SomeQuantity(20)

        val q4 = AnotherQuantity(10)
        val q5 = SomeQuantity(10)

        //Equals Check
        assertThat(q1 == q2).isTrue
        assertThat(q1.equals("Some String")).isFalse
        assertThat(q1 != q3).isTrue
        assertThat(q1.equals(q4)).isFalse

        //Reflexion
        assertThat(q1 == q1).isTrue

        //Symmetric
        assertThat(q1 == q2).isTrue
        assertThat(q2 == q1).isTrue

        //Transitivity
        assertThat(q1 == q2).isTrue
        assertThat(q2 == q5).isTrue
        assertThat(q1 == q5).isTrue

        //Consistency
        assertThat(q1 == q2).isTrue
        assertThat(q1 == q2).isTrue
        assertThat(q1 == q2).isTrue

        //Equals with null
        assertThat(q1.equals(null)).isFalse
    }

    @Test
    fun hashCodeTest() {
        val q1 = SomeQuantity(10)
        val q2 = SomeQuantity(10)
        val q3 = SomeQuantity(20)

        //HashCode check
        val h1 = q1.hashCode()
        val h11 = q1.hashCode()
        val h12 = q1.hashCode()

        assertEquals(h1, h11)
        assertEquals(h11, h1)
        assertEquals(h12, h1)

        assertThat(q1.hashCode()).isEqualTo(q2.hashCode())
        assertThat(q1.hashCode()).isNotEqualTo(q3.hashCode())
    }
}