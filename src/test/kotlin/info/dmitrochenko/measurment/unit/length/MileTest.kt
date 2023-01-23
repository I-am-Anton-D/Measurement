package info.dmitrochenko.measurment.unit.length

import info.dmitrochenko.measurment.quantity.meter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

// 1609.344

internal class MileTest {
    @Test
    fun mileTest() {
        val mile = 1.mile()
        assertThat(mile.value).isEqualTo(BigDecimal("1609.344"))

        val l = 1609.344.meter()
        assertThat(l.toMile()).isEqualTo(BigDecimal("1.000000000000000"))
    }
}