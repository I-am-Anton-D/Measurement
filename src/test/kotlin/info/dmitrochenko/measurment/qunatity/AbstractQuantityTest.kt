package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import info.dmitrochenko.measurment.abstracts.AbstractUnit
import info.dmitrochenko.measurment.abstracts.MetricUnit
import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.quantity.*
import info.dmitrochenko.measurment.unit.length.Meter
import info.dmitrochenko.measurment.unit.length.Mile
import info.dmitrochenko.measurment.unit.length.mile
import java.math.BigDecimal
import java.text.DecimalFormat
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

    @Test
    fun divideTest() {
        val length = 10.meter()
        val time = 10.second()

        val velocity = length / time

        assertThat(velocity.value).isEqualTo(BigDecimal("1"))
        assertThat(velocity).isInstanceOf(Velocity::class.java)
    }

    @Test
    fun timesTest() {
        val length = 10.meter()
        val area = length * length

        assertThat(area.value).isEqualTo(BigDecimal("100"))
        assertThat(area).isInstanceOf(Area::class.java)
    }

    @Test
    fun plusTest() {
        val length = 10.meter()
        val miles = 10.mile()
        val km = 10.km()
        val sum = km + miles + length
        assertThat(sum.value).isEqualTo(BigDecimal("26103.440"))
    }

    @Test
    fun quantityTimesTest() {
        val q1 = SomeQuantity(10)
        val q2 = AnotherQuantity(20)
        val times = q1 * q2
        assertThat(times.value).isEqualTo(BigDecimal("200"))

        val divide = q2 / q1
        assertThat(divide.value).isEqualTo(BigDecimal("2"))
    }

    @Test
    fun valueInTest() {
        val meters = 1609.344.meter()
        val mile = meters.valueIn(Mile)
        assertThat(mile).isEqualTo(BigDecimal("1.000000000000000"))

        val miles = 1.mile()
        val inMeters = miles.valueIn(Meter, prefix = Prefix.KILO )
        assertThat(inMeters).isEqualTo(BigDecimal("1.609344"))

        val inMetersDef = miles.valueIn(Meter)
        assertThat(inMetersDef).isEqualTo(BigDecimal("1609.344"))

        val fromDimension = miles.valueIn(Meter.toDimension())
        assertThat(fromDimension).isEqualTo(BigDecimal("1609.344"))

        assertThat(meters.dimension).isEqualTo(Dimension<Length>(Meter))

    }

    @Test
    fun toStringTest() {
        val length = 1609.344.meter()
        Locale.setDefault(Locale("ru", "RU"))
        assertThat(length.toString()).isEqualTo("1609.344 м")
        assertThat(length.toString(Mile)).isEqualTo("1 миль")
        assertThat(length.toString(Mile.toDimension())).isEqualTo("1 миль")
        assertThat(length.toString(dimension = null, valueFormat = null, locale = Locale.getDefault())).isEqualTo("1609.344 м")
        assertThat(length.toString(Mile, valueFormat = DecimalFormat("##.#"))).isEqualTo("1 миль")
    }
}
