package info.dmitrochenko.measurment.unit.mass

import info.dmitrochenko.measurment.dimension.Prefix
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

internal class GramTest {

    @Test
    fun getSymbol() {
        Locale.setDefault(Locale("en", "GB"))
        assertThat(Gram.symbol()).isEqualTo("g")

        assertThat(Gram.symbol(Locale("ru", "RU"))).isEqualTo("г")
    }

    @Test
    fun gramTest() {
        val g = 1.gram()
        assertThat(g.value).isEqualTo(BigDecimal("0.001"))

        val m = 1.gram(Prefix.KILO)
        assertThat(m.value).isEqualTo(BigDecimal("1"))
        assertThat(m.toStringDimension.getHoldersList()[0].prefix).isEqualTo(Prefix.KILO)

        val ng = 1.ng()
        assertThat(ng.value).isEqualTo(BigDecimal("0.000000000001"))
        assertThat(ng.toStringDimension.getHoldersList()[0].prefix).isEqualTo(Prefix.NANO)
    }
}