package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.quantity.*
import info.dmitrochenko.measurment.unit.time.Day
import info.dmitrochenko.measurment.unit.time.Hour
import info.dmitrochenko.measurment.unit.time.Minute
import info.dmitrochenko.measurment.unit.time.Second
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


internal class TimeTest {

    @Test
    fun newInstanceTest() {
        val t1 = Time(10)
        val t2 = Time(20)
        val t3 = t1 + t2
        assertThat(t3.value).isEqualTo(BigDecimal("30"))

        val t4 = Time(20, Hour.toDimension())
        assertThat(t4.toStringDimension).isEqualTo(Hour.toDimension())
    }

    @Test
    fun extTest() {
        val h = 1.hour()
        val m = 1.minute()
        val s = 1.second()
        val d = 1.day()

        assertThat(h.toStringDimension).isEqualTo(Hour.toDimension())
        assertThat(m.toStringDimension).isEqualTo(Minute.toDimension())
        assertThat(s.toStringDimension).isEqualTo(Second.toDimension())
        assertThat(d.toStringDimension).isEqualTo(Day.toDimension())

        val t = 1.timeIn(Minute)
        assertThat(t.value).isEqualTo(BigDecimal("60"))

        val t2 = 1.timeIn(Hour)
        assertThat(t2.value).isEqualTo(BigDecimal("3600"))
    }


}