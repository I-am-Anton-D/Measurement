package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.quantity.*
import info.dmitrochenko.measurment.unit.length.Meter
import info.dmitrochenko.measurment.unit.length.Mile
import info.dmitrochenko.measurment.unit.time.Hour
import info.dmitrochenko.measurment.unit.time.Second
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


internal class VelocityTest {

    @Test
    fun newInstanceTest() {
        val v1 = Velocity(10)
        val v2 = Velocity(20)
        val v3 = v1 + v2
        assertThat(v3.value).isEqualTo(BigDecimal("30"))

        val v4 = Velocity(20, Velocity.mph())
        assertThat(v4.toStringDimension).isEqualTo(Velocity.mph())
    }

    @Test
    fun divTest() {
        val v = 10.msec()
        val time = 5.second()

        val length = v / time

        assertThat(length.value).isEqualTo(BigDecimal("2"))
        assertThat(length).isInstanceOf(Acceleration::class.java)
    }


    @Test
    fun companionTest() {
        val msec = Velocity.msec()
        val mph = Velocity.mph()
        val kmh = Velocity.kmh()

        assertThat(msec.getHoldersList()[0].unit).isInstanceOf(Meter::class.java)
        assertThat(msec.getHoldersList()[1].unit).isInstanceOf(Second::class.java)

        assertThat(mph.getHoldersList()[0].unit).isInstanceOf(Mile::class.java)
        assertThat(mph.getHoldersList()[1].unit).isInstanceOf(Hour::class.java)

        assertThat(kmh.getHoldersList()[0].unit).isInstanceOf(Meter::class.java)
        assertThat(kmh.getHoldersList()[1].unit).isInstanceOf(Hour::class.java)
    }

    @Test
    fun extTest() {
        val msec = 1.msec()
        val kmh = 1.kmh()
        val mph = 1.mph()
        val v = 1.velocityFrom(Velocity.mph())

        assertThat(msec.toStringDimension).isEqualTo(Velocity.msec())
        assertThat(kmh.toStringDimension).isEqualTo(Velocity.kmh())
        assertThat(mph.toStringDimension).isEqualTo(Velocity.mph())
        assertThat(v.toStringDimension).isEqualTo(Velocity.mph())
    }

}