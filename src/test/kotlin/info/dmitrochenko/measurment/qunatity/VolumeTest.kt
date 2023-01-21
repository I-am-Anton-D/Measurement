package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.quantity.Area
import info.dmitrochenko.measurment.quantity.Length
import info.dmitrochenko.measurment.quantity.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class VolumeTest {

    @Test
    fun newInstanceTest() {
        val vol1 = Volume(10)
        val vol2 = Volume(20)
        val vol3 = vol1 + vol2
        assertThat(vol3.value).isEqualTo(BigDecimal("30"))
    }

    @Test
    fun divideTest() {
        val vol = Volume(30)
        val length = Length(10)
        val area = Area(10)

        val areaFromVolume = vol / length
        assertThat(areaFromVolume.value).isEqualTo(BigDecimal("3"))
        assertThat(areaFromVolume).isInstanceOf(Area::class.java)

        val lenFromVolume = vol / area
        assertThat(lenFromVolume.value).isEqualTo(BigDecimal("3"))
        assertThat(lenFromVolume).isInstanceOf(Length::class.java)
    }

}