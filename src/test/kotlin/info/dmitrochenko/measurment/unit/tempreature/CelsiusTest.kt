package info.dmitrochenko.measurment.unit.tempreature

import info.dmitrochenko.measurment.quantity.Temperature
import info.dmitrochenko.measurment.unit.temperature.celsius
import info.dmitrochenko.measurment.unit.temperature.toCelsius
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

//273.15
internal class CelsiusTest {

    @Test
    fun celsiusTest() {
        val t = 0.celsius()
        assertThat(t.value).isEqualTo(BigDecimal("273.15"))

        val t1 = Temperature(0)
        assertThat(t1.toCelsius()).isEqualTo(BigDecimal("-273.15"))
    }
}