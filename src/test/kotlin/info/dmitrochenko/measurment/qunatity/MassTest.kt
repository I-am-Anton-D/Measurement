package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.quantity.*
import info.dmitrochenko.measurment.unit.mass.Gram
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class MassTest {
    @Test
    fun newInstanceTest() {
        val m1 = Mass(10)
        val m2 = Mass(20)
        val m3 = m1 + m2
        assertThat(m3.value).isEqualTo(BigDecimal("30"))

        val m4 = Mass(20, Mass.kg())
        assertThat(m4.dimension).isEqualTo(Mass.kg())
    }

    @Test
    fun times() {
        val acc = Acceleration(10)
        val m = Mass(1)

        val f = m * acc
        assertThat(f.value).isEqualTo(BigDecimal(10))
        assertThat(f).isInstanceOf(Force::class.java)
    }

    @Test
    fun extTest() {
        val m = 1.kg()
        assertThat(m.value).isEqualTo(BigDecimal(1))

        val m2 = 1.massFrom(Gram.prefix(Prefix.KILO))
        assertThat(m2.value).isEqualTo(BigDecimal(1))
    }
}