package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.quantity.Area
import info.dmitrochenko.measurment.quantity.Length
import info.dmitrochenko.measurment.quantity.Volume
import info.dmitrochenko.measurment.quantity.sqmeter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class AreaTest {

    @Test
    fun newInstanceTest() {
        val area1 = Area(10)
        val area2 = Area(20)
        val area3 = area1 + area2
        assertThat(area3.value).isEqualTo(BigDecimal("30"))
    }

    @Test
    fun divAndTimesTest() {
        val area = Area(10)
        val length = Length(1)

        val volume = area * length
        assertThat(volume.value).isEqualTo(BigDecimal("10"))
        assertThat(volume).isInstanceOf(Volume::class.java)

        val len = area / length
        assertThat(len.value).isEqualTo(BigDecimal("10"))
        assertThat(len).isInstanceOf(Length::class.java)

    }

    @Test
    fun extTest() {
        val area = 10.sqmeter()
        assertThat(area.value).isEqualTo(BigDecimal("10"))
    }
}