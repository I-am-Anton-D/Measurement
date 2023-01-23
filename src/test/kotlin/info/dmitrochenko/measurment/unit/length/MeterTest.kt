package info.dmitrochenko.measurment.unit.length

import info.dmitrochenko.measurment.dimension.Prefix
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class MeterTest {

    @Test
    fun meterTest() {
        val km = Meter.KILO
        val nm = Meter.NANO

        assertThat(km.getSingleUnit()).isEqualTo(Meter)
        assertThat(km.getHoldersList()[0].prefix).isEqualTo(Prefix.KILO)

        assertThat(nm.getSingleUnit()).isEqualTo(Meter)
        assertThat(nm.getHoldersList()[0].prefix).isEqualTo(Prefix.NANO)


    }
}