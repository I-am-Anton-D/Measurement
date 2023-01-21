package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.quantity.*
import info.dmitrochenko.measurment.unit.length.Mile
import info.dmitrochenko.measurment.unit.time.Second
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class ForceTest {

    @Test
    fun newInstanceTest() {
        val f1 = Force(10)
        val f2 = Force(20)
        val f3 = f1 + f2
        assertThat(f3.value).isEqualTo(BigDecimal("30"))

        val newDim = Dimension<Force>(Mile.pow(1), Second.pow(-2))
        val f4 = Force(10, newDim)
        assertThat(f4.toStringDimension).isEqualTo(newDim)

        val velocity = 1.msec()
        val time = 1.second()

        val acc = Acceleration(velocity, time)
        val kg = 1.kg()

        val f5 = Force(kg, acc)

        assertThat(f5.value).isEqualTo("1")
    }
}