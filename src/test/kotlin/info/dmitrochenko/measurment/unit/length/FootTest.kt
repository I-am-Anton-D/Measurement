package info.dmitrochenko.measurment.unit.length

import info.dmitrochenko.measurment.quantity.meter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


//0.3048
internal class FootTest {

    @Test
    fun footTest() {
        val foot = 1.foot()
        assertThat(foot.value).isEqualTo(BigDecimal("0.3048"))

        val l = 0.3048.meter()
        assertThat(l.toFoot()).isEqualTo("1.000000000000000")
    }

}