package info.dmitrochenko.measurment.qunatity

import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.quantity.Quantity
import info.dmitrochenko.measurment.unit.length.Meter
import info.dmitrochenko.measurment.unit.time.Second
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


internal class QuantityTest {

    @Test
    fun newInstanceTest() {
        val dim = Dimension<Quantity>(Second, Meter)
        val q1 = Quantity(10, dim)
        val q2 = Quantity(30, dim)
        val q3 = q1 + q2
        assertThat(q3.value).isEqualTo(BigDecimal("40"))
    }


}