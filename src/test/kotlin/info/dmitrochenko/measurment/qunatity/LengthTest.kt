package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.quantity.*
import info.dmitrochenko.measurment.unit.length.Mile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


internal class LengthTest {

    @Test
    fun newInstanceTest() {
        val l1 = Length(10)
        val l2 = Length(30)
        val l3 = l1 + l2
        assertThat(l3.value).isEqualTo(BigDecimal("40"))

        val l4 = Length(20, Mile.toDimension())
        assertThat(l4.toStringDimension).isEqualTo(Mile.toDimension())
    }

    @Test
    fun timesAndDivTest() {
        val l = 1.meter()
        val time = 1.second()

        val velocity = l / time
        assertThat(velocity).isInstanceOf(Velocity::class.java)

        val area = l * l
        assertThat(area).isInstanceOf(Area::class.java)
    }

    @Test
    fun extTests() {
        val km = 1.km()
        val cm = 1.cm()
        val mm = 1.mm()

        val l = 1.lengthIn(Mile.toDimension())

        assertThat(km.value).isEqualTo(BigDecimal("1000"))
        assertThat(km.toStringDimension.getHoldersList()[0].prefix).isEqualTo(Prefix.KILO)

        assertThat(cm.value).isEqualTo(BigDecimal("0.01"))
        assertThat(cm.toStringDimension.getHoldersList()[0].prefix).isEqualTo(Prefix.CENTI)

        assertThat(mm.value).isEqualTo(BigDecimal("0.001"))
        assertThat(mm.toStringDimension.getHoldersList()[0].prefix).isEqualTo(Prefix.MILLI)

        assertThat(l.toStringDimension).isEqualTo(Mile.toDimension())

    }

}