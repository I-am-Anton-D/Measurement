package info.dmitrochenko.measurment.dimension


import info.dmitrochenko.measurment.dimension.Prefix.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

internal class PrefixTest {
    @Test
    fun prefixExponentText() {
        assertThat(YOTTA.exponent).isEqualTo(24)
        assertThat(ZETTA.exponent).isEqualTo(21)
        assertThat(EXA.exponent  ).isEqualTo(18)
        assertThat(PETA.exponent ).isEqualTo(15)
        assertThat(TERA.exponent ).isEqualTo(12)
        assertThat(GIGA.exponent ).isEqualTo(9)
        assertThat(MEGA.exponent ).isEqualTo(6)
        assertThat(KILO.exponent ).isEqualTo(3)
        assertThat(HECTO.exponent).isEqualTo(2)
        assertThat(DEKA.exponent ).isEqualTo(1)
        assertThat(NOMINAL.exponent).isEqualTo(0)
        assertThat(DECI.exponent ).isEqualTo(-1)
        assertThat(CENTI.exponent).isEqualTo(-2)
        assertThat(MILLI.exponent).isEqualTo(-3)
        assertThat(MICRO.exponent).isEqualTo(-6)
        assertThat(NANO.exponent ).isEqualTo(-9)
        assertThat(PICO.exponent ).isEqualTo(-12)
        assertThat(FEMTO.exponent).isEqualTo(-15)
        assertThat(ATTO.exponent ).isEqualTo(-18)
        assertThat(ZEPTO.exponent).isEqualTo(-21)
        assertThat(YOCTO.exponent).isEqualTo(-24)
        
    }

    @Test
    fun prefixSymbolText() {
        Locale.setDefault(Locale("en", "GB"))
        assertThat(YOTTA.symbol()).isEqualTo("Y")
        assertThat(ZETTA.symbol()).isEqualTo("Z")
        assertThat(EXA.symbol()  ).isEqualTo("E")
        assertThat(PETA.symbol() ).isEqualTo("P")
        assertThat(TERA.symbol() ).isEqualTo("T")
        assertThat(GIGA.symbol() ).isEqualTo("G")
        assertThat(MEGA.symbol() ).isEqualTo("M")
        assertThat(KILO.symbol() ).isEqualTo("k")
        assertThat(HECTO.symbol()).isEqualTo("h")
        assertThat(DEKA.symbol() ).isEqualTo("da")
        assertThat(NOMINAL.symbol()).isEqualTo("")
        assertThat(DECI.symbol() ).isEqualTo("d")
        assertThat(CENTI.symbol()).isEqualTo("c")
        assertThat(MILLI.symbol()).isEqualTo("m")
        assertThat(MICRO.symbol()).isEqualTo("μ")
        assertThat(NANO.symbol() ).isEqualTo("n")
        assertThat(PICO.symbol() ).isEqualTo("p")
        assertThat(FEMTO.symbol()).isEqualTo("f")
        assertThat(ATTO.symbol() ).isEqualTo("a")
        assertThat(ZEPTO.symbol()).isEqualTo("z")
        assertThat(YOCTO.symbol()).isEqualTo("y")
    }

    @Test
    fun prefixNameText() {
        Locale.setDefault(Locale("en", "GB"))
        assertThat(YOTTA.name()).isEqualTo("yotta")
        assertThat(ZETTA.name()).isEqualTo("zetta")
        assertThat(EXA.name()  ).isEqualTo("exa")
        assertThat(PETA.name() ).isEqualTo("peta")
        assertThat(TERA.name() ).isEqualTo("tera")
        assertThat(GIGA.name() ).isEqualTo("giga")
        assertThat(MEGA.name() ).isEqualTo("mega")
        assertThat(KILO.name() ).isEqualTo("kilo")
        assertThat(HECTO.name()).isEqualTo("hecto")
        assertThat(DEKA.name() ).isEqualTo("deka")
        assertThat(NOMINAL.name()).isEqualTo("")
        assertThat(DECI.name() ).isEqualTo("deci")
        assertThat(CENTI.name()).isEqualTo("centi")
        assertThat(MILLI.name()).isEqualTo("milli")
        assertThat(MICRO.name()).isEqualTo("micro")
        assertThat(NANO.name() ).isEqualTo("nano")
        assertThat(PICO.name() ).isEqualTo("pico")
        assertThat(FEMTO.name()).isEqualTo("femto")
        assertThat(ATTO.name() ).isEqualTo("atto")
        assertThat(ZEPTO.name()).isEqualTo("zepto")
        assertThat(YOCTO.name()).isEqualTo("yocto")
    }

    @Test
    fun getPrefixMultiplierTest() {
        assertThat(KILO.multiplier() ).isEqualTo(BigDecimal(1000))
        assertThat(MEGA.multiplier() ).isEqualTo(BigDecimal(1000000))
        assertThat(MILLI.multiplier() ).isEqualTo(BigDecimal("0.001"))
    }

    @Test
    fun getNominalValueTest() {
        assertThat(KILO.inNominal(1)).isEqualTo(BigDecimal(1000))
        assertThat(NOMINAL.inNominal(1)).isEqualTo(BigDecimal.ONE)
    }
}