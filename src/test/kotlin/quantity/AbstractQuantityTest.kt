package quantity

import unit.Prefix
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import unit.AbstractUnit
import unit.MetricUnit
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

@Suppress("KotlinConstantConditions")
internal class AbstractQuantityTest {

    object SomeUnit : MetricUnit<SomeQuantity>() {
        override fun symbol(locale: Locale): String {
            return "some symbol"
        }

        override fun singularForm(locale: Locale): String {
            return "some singular"
        }

        override fun pluralForm(locale: Locale): String {
            return "some plural"
        }

    }

    object AnotherUnit : MetricUnit<SomeQuantity>(2) {
        override fun symbol(locale: Locale): String {
            return "another symbol"
        }

        override fun singularForm(locale: Locale): String {
            return "another singular"
        }

        override fun pluralForm(locale: Locale): String {
            return "another plural"
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
        assertThat(s.valueIn(unit = AnotherUnit)).isEqualTo(BigDecimal(500.toString()))
    }

    @Test
    fun infixMethodTest() {
        val result = 1000.some() to AnotherUnit

        assertThat(result).isInstanceOf(BigDecimal::class.java)
        assertThat(result).isEqualTo(BigDecimal(500))
    }

    @Test
    fun plusTest() {
        val q1 = SomeQuantity(10)
        val q2 = SomeQuantity(BigDecimal.TEN)

        val sum = q1 + q2

        assertThat(sum).isInstanceOf(SomeQuantity::class.java)
        assertThat(sum.value).isEqualTo(BigDecimal(20))
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
        val q1 = SomeQuantity(10);

        assertThat(q1.toString(expand = false)).isEqualTo("10 " + SomeUnit.symbol())
        assertThat(q1.toString(expand = true)).isEqualTo("10 " + SomeUnit.pluralForm())

        assertThat(q1.toString(expand = true, df = DecimalFormat("#!"))).isEqualTo("10! " + SomeUnit.pluralForm())
        assertThat(q1.toString(expand = false, df = DecimalFormat("#!"))).isEqualTo("10! " + SomeUnit.symbol())
        assertThat(q1.toString(df = DecimalFormat("#!"))).isEqualTo("10! " + SomeUnit.symbol())


        assertThat(q1.toString(expand = true, df = DecimalFormat("#!"), locale = Locale("ru", "RU"))).isEqualTo("10! " + SomeUnit.pluralForm())
        assertThat(q1.toString(expand = false, df = DecimalFormat("#!"), locale = Locale("ru", "RU"))).isEqualTo("10! " + SomeUnit.symbol())
        assertThat(q1.toString(df = DecimalFormat("#!"), locale = Locale("ru", "RU"))).isEqualTo("10! " + SomeUnit.symbol())
        assertThat(q1.toString(locale = Locale("ru", "RU"))).isEqualTo("10 " + SomeUnit.symbol())
        assertThat(q1.toString(expand = false, locale = Locale("ru", "RU"))).isEqualTo("10 " + SomeUnit.symbol())
        assertThat(q1.toString(expand = true, locale = Locale("ru", "RU"))).isEqualTo("10 " + SomeUnit.pluralForm())

        val q2 = SomeQuantity(1000)
        assertThat(q2.toString(unit = AnotherUnit)).isEqualTo("500 " + AnotherUnit.symbol())
        assertThat(q2.toString(unit = AnotherUnit, expand = true)).isEqualTo("500 " + AnotherUnit.pluralForm())
        assertThat(q2.toString(unit = AnotherUnit, expand = false)).isEqualTo("500 " + AnotherUnit.symbol())

        assertThat(q2.toString(unit = AnotherUnit, df = DecimalFormat("#!"))).isEqualTo("500! " + AnotherUnit.symbol())
        assertThat(q2.toString(unit = AnotherUnit, df = DecimalFormat("#!"), locale = Locale("ru", "RU"))).isEqualTo("500! " + AnotherUnit.symbol())
        assertThat(q2.toString(unit = AnotherUnit, df = DecimalFormat("#!"), locale = Locale("ru", "RU") , expand = false)).isEqualTo("500! " + AnotherUnit.symbol())
        assertThat(q2.toString(unit = AnotherUnit, df = DecimalFormat("#!"), locale = Locale("ru", "RU") , expand = true)).isEqualTo("500! " + AnotherUnit.pluralForm())
        assertThat(q2.toString(unit = AnotherUnit, df = DecimalFormat("#!"), expand = true)).isEqualTo("500! " + AnotherUnit.pluralForm())
        assertThat(q2.toString(unit = AnotherUnit, df = DecimalFormat("#!"), expand = false)).isEqualTo("500! " + AnotherUnit.symbol())


        Locale.setDefault(Locale("en","GB"))
        assertThat(q2.toString(unit = SomeUnit, prefix = Prefix.KILO)).isEqualTo("1 k" + SomeUnit.symbol())
        assertThat(q2.toString(unit = SomeUnit, prefix = Prefix.MEGA)).isEqualTo("0.001 M" + SomeUnit.symbol())
        assertThat(q2.toString(unit = AnotherUnit, prefix = Prefix.KILO)).isEqualTo("0.5 k" + AnotherUnit.symbol())

        assertThat(q2.toString(unit = SomeUnit, prefix = Prefix.KILO, expand = true)).isEqualTo("1 kilo" + SomeUnit.pluralForm())
        assertThat(q2.toString(unit = SomeUnit, prefix = Prefix.MEGA, expand = true)).isEqualTo("0.001 mega" + SomeUnit.pluralForm())
        assertThat(q2.toString(unit = AnotherUnit, prefix = Prefix.KILO, expand = true)).isEqualTo("0.5 kilo" + AnotherUnit.pluralForm())

        assertThat(q2.toString(unit = SomeUnit, prefix = Prefix.KILO, expand = true, locale = Locale("ru", "RU"))).isEqualTo("1 кило" + SomeUnit.pluralForm())
        assertThat(q2.toString(unit = SomeUnit, prefix = Prefix.MEGA, expand = true, locale = Locale("ru", "RU"))).isEqualTo("0.001 мега" + SomeUnit.pluralForm())
        assertThat(q2.toString(unit = AnotherUnit, prefix = Prefix.KILO, expand = true, locale = Locale("ru", "RU"))).isEqualTo("0.5 кило" + AnotherUnit.pluralForm())

        assertThat(q2.toString(unit = SomeUnit, prefix = Prefix.KILO, expand = true, locale = Locale("ru", "RU"), df = DecimalFormat("#!"))).isEqualTo("1! кило" + SomeUnit.pluralForm())
        assertThat(q2.toString(unit = SomeUnit, prefix = Prefix.MEGA, expand = true, locale = Locale("ru", "RU"), df = DecimalFormat("#.###!"))).isEqualTo("0.001! мега" + SomeUnit.pluralForm())
        assertThat(q2.toString(unit = AnotherUnit, prefix = Prefix.KILO, expand = true, locale = Locale("ru", "RU"), df = DecimalFormat("#.#!"))).isEqualTo("0.5! кило" + AnotherUnit.pluralForm())
    }

    object NotMetricUnit : AbstractUnit<SomeQuantity>() {
        override fun symbol(locale: Locale): String {
            return "notmetric"
        }
    }

    @Test
    fun toStringWithQParamsAndNullUnits() {
        val q1 = 10.some()
        assertThat(q1.toString(ToStringParameters(expand = false))).isEqualTo("10 "+ SomeUnit.symbol())

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