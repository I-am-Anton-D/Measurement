package unit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import unit.mass.Gram
import java.util.*

internal class GramTest {

    @Test
    fun getSymbol() {
        Locale.setDefault(Locale("en","GB"))
        assertThat(Gram.symbol()).isEqualTo("g")

        assertThat(Gram.symbol(Locale("ru","RU"))).isEqualTo("г")
    }
}