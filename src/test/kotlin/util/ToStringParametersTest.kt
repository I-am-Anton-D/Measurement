package util

import exception.IllegalMetricPrefix
import measurand.Length
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import quantity.ToStringParameters
import unit.length.Meter
import unit.length.Mile

import unit.prototype.Prefix
import java.text.DecimalFormat
import java.util.*

internal class ToStringParametersTest {

    @Test
    fun createDefaultTest() {
        val params = ToStringParameters<Length>()
        assertThat(params.df).isEqualTo(DecimalFormat())
        assertThat(params.locale).isEqualTo(Locale.getDefault())
        assertThat(params.unit).isNull()
        assertThat(params.prefix).isEqualTo(Prefix.NOMINAL)
        assertThat(params.expand).isFalse

        val params2 = ToStringParameters(df = DecimalFormat("#"), unit = Meter, prefix = Prefix.MEGA, expand = true, locale = Locale("ru","RU"))

        assertThat(params2.df).isEqualTo(DecimalFormat("#"))
        assertThat(params2.locale).isEqualTo(Locale("ru", "RU"))
        assertThat(params2.unit).isEqualTo(Meter)
        assertThat(params2.prefix).isEqualTo(Prefix.MEGA)
        assertThat(params2.expand).isTrue

        val params3 = ToStringParameters(unit = Meter)
        assertThat(params3.unit).isEqualTo(Meter)
    }


    @Test
    fun createWithParamsTest() {
        val params = ToStringParameters(df = DecimalFormat("#"), locale = Locale("en", "GB"), unit = Meter, prefix = Prefix.GIGA, expand = true)
        assertThat(params.df).isNotEqualTo(DecimalFormat())
        assertThat(params.locale).isEqualTo(Locale("en","GB"))
        assertThat(params.unit).isEqualTo(Meter)
        assertThat(params.prefix).isEqualTo(Prefix.GIGA)
        assertThat(params.expand).isTrue
    }

    @Test
    fun mutableParamsTest() {
        val params = ToStringParameters<Length>()

        params.df = DecimalFormat("#")
        assertThat(params.df).isEqualTo(DecimalFormat("#"))

        params.locale = Locale("en", "GB")
        assertThat(params.locale).isEqualTo(Locale("en","GB"))

        params.expand = false

        assertThrows<IllegalMetricPrefix>{
             params.prefix = Prefix.GIGA
        }

        params.unit = Meter
        assertThat(params.prefix).isEqualTo(Prefix.NOMINAL)

        params.prefix = Prefix.KILO
        assertThat(params.prefix).isEqualTo(Prefix.KILO)

        params.unit = Mile
        assertThat(params.prefix).isEqualTo(Prefix.NOMINAL)

        assertThrows<IllegalMetricPrefix>{
            params.prefix = Prefix.GIGA
        }

        assertDoesNotThrow {
            params.prefix = Prefix.NOMINAL
        }
    }


}