package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.quantity.Acceleration
import info.dmitrochenko.measurment.quantity.minute
import info.dmitrochenko.measurment.quantity.mph
import info.dmitrochenko.measurment.unit.length.Mile
import info.dmitrochenko.measurment.unit.time.Second
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class AccelerationTest {

    @Test
    fun newInstanceTest() {
        val acc1 = Acceleration(10)
        val acc2 = Acceleration(20)
        val acc3 = acc1 + acc2
        assertThat(acc3.value).isEqualTo(BigDecimal("30"))

        val newDim = Dimension<Acceleration>(Mile.pow(1), Second.pow(-2))
        val acc4 = Acceleration(10, newDim)
        assertThat(acc4.toStringDimension).isEqualTo(newDim)

        val velocity = 12.mph()
        val time = 20.minute()

        val acc5 = Acceleration(velocity, time)
        assertThat(acc5.value).isEqualTo("0.0044704")
    }
}