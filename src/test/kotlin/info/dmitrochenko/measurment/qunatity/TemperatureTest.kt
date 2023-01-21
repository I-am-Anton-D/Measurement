package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.quantity.Temperature
import info.dmitrochenko.measurment.quantity.kelvin
import info.dmitrochenko.measurment.unit.temperature.Celsius
import info.dmitrochenko.measurment.unit.temperature.celsius
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class TemperatureTest {

    @Test
    fun newInstanceTest() {
        val t1 = Temperature(10)
        val t2 = Temperature(20)
        val t3 = t1 + t2
        assertThat(t3.value).isEqualTo(BigDecimal("30"))

        val t4 = Temperature(10, Celsius.toDimension())
        assertThat(t4.toStringDimension).isEqualTo(Celsius.toDimension())

        val t5 = 10.kelvin()
        assertThat(t5.value).isEqualTo(BigDecimal("10"))

        val t6 = 10.kelvin(Prefix.KILO)
        assertThat(t6.value).isEqualTo(BigDecimal("10000"))

        val t7 = 10.celsius()
        assertThat(t7.toStringDimension).isEqualTo(Celsius.toDimension())
    }
}