package info.dmitrochenko.measurment.unit.tempreature

import info.dmitrochenko.measurment.quantity.Temperature
import info.dmitrochenko.measurment.unit.temperature.fahrenheit
import info.dmitrochenko.measurment.unit.temperature.toFahrenheit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


internal class FahrenheitTest {
    @Test
    fun fahrenheitTest() {
        val t = 0.fahrenheit()
        assertThat(t.value).isEqualTo(BigDecimal("255.372222196685"))

        val t1 = Temperature(0)
        assertThat(t1.toFahrenheit()).isEqualTo(BigDecimal("-459.6700000000000"))
    }
}