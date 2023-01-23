package info.dmitrochenko.measurment.unit.length

import info.dmitrochenko.measurment.quantity.meter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

// 0.0254
internal class InchTest {

    @Test
    fun inchTest() {
        val inch = 1.inch()
        assertThat(inch.value).isEqualTo(BigDecimal("0.0254"))

        val l = 0.0254.meter()
        assertThat(l.toInch()).isEqualTo("1.000000000000000")

    }

    //Just for coverage
    @Test
    fun staticFractionTest() {
        val in1 = Inch.OneHalf
        val in2 = Inch.OneEighth
        val in3 = Inch.OneQuarter
        val in4 = Inch.OneSixteenth

        assertThat(in1.toStringDimension).isEqualTo(Inch.toDimension())
        assertThat(in2.toStringDimension).isEqualTo(Inch.toDimension())
        assertThat(in3.toStringDimension).isEqualTo(Inch.toDimension())
        assertThat(in4.toStringDimension).isEqualTo(Inch.toDimension())

        val in5 = Inch.ThreeEighth
        val in6 = Inch.ThreeQuarter
        val in7 = Inch.FiveEighth
        val in8 = Inch.SevenEighth

        assertThat(in5.toStringDimension).isEqualTo(Inch.toDimension())
        assertThat(in6.toStringDimension).isEqualTo(Inch.toDimension())
        assertThat(in7.toStringDimension).isEqualTo(Inch.toDimension())
        assertThat(in8.toStringDimension).isEqualTo(Inch.toDimension())
    }
}