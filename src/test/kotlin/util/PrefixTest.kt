package util

import unit.Prefix.*
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
        assertThat(YOTTA.prefixSymbol()).isEqualTo("Y")
        assertThat(ZETTA.prefixSymbol()).isEqualTo("Z")
        assertThat(EXA.prefixSymbol()  ).isEqualTo("E")
        assertThat(PETA.prefixSymbol() ).isEqualTo("P")
        assertThat(TERA.prefixSymbol() ).isEqualTo("T")
        assertThat(GIGA.prefixSymbol() ).isEqualTo("G")
        assertThat(MEGA.prefixSymbol() ).isEqualTo("M")
        assertThat(KILO.prefixSymbol() ).isEqualTo("k")
        assertThat(HECTO.prefixSymbol()).isEqualTo("h")
        assertThat(DEKA.prefixSymbol() ).isEqualTo("da")
        assertThat(NOMINAL.prefixSymbol()).isEqualTo("")
        assertThat(DECI.prefixSymbol() ).isEqualTo("d")
        assertThat(CENTI.prefixSymbol()).isEqualTo("c")
        assertThat(MILLI.prefixSymbol()).isEqualTo("m")
        assertThat(MICRO.prefixSymbol()).isEqualTo("μ")
        assertThat(NANO.prefixSymbol() ).isEqualTo("n")
        assertThat(PICO.prefixSymbol() ).isEqualTo("p")
        assertThat(FEMTO.prefixSymbol()).isEqualTo("f")
        assertThat(ATTO.prefixSymbol() ).isEqualTo("a")
        assertThat(ZEPTO.prefixSymbol()).isEqualTo("z")
        assertThat(YOCTO.prefixSymbol()).isEqualTo("y")
    }

    @Test
    fun prefixNameText() {
        Locale.setDefault(Locale("en", "GB"))
        assertThat(YOTTA.prefixName()).isEqualTo("yotta")
        assertThat(ZETTA.prefixName()).isEqualTo("zetta")
        assertThat(EXA.prefixName()  ).isEqualTo("exa")
        assertThat(PETA.prefixName() ).isEqualTo("peta")
        assertThat(TERA.prefixName() ).isEqualTo("tera")
        assertThat(GIGA.prefixName() ).isEqualTo("giga")
        assertThat(MEGA.prefixName() ).isEqualTo("mega")
        assertThat(KILO.prefixName() ).isEqualTo("kilo")
        assertThat(HECTO.prefixName()).isEqualTo("hecto")
        assertThat(DEKA.prefixName() ).isEqualTo("deka")
        assertThat(NOMINAL.prefixName()).isEqualTo("")
        assertThat(DECI.prefixName() ).isEqualTo("deci")
        assertThat(CENTI.prefixName()).isEqualTo("centi")
        assertThat(MILLI.prefixName()).isEqualTo("milli")
        assertThat(MICRO.prefixName()).isEqualTo("micro")
        assertThat(NANO.prefixName() ).isEqualTo("nano")
        assertThat(PICO.prefixName() ).isEqualTo("pico")
        assertThat(FEMTO.prefixName()).isEqualTo("femto")
        assertThat(ATTO.prefixName() ).isEqualTo("atto")
        assertThat(ZEPTO.prefixName()).isEqualTo("zepto")
        assertThat(YOCTO.prefixName()).isEqualTo("yocto")
    }

    @Test
    fun getPrefixMultiplierTest() {
        assertThat(KILO.getPrefixMultiplier() ).isEqualTo(BigDecimal(1000))
        assertThat(MEGA.getPrefixMultiplier() ).isEqualTo(BigDecimal(1000000))
        assertThat(MILLI.getPrefixMultiplier() ).isEqualTo(BigDecimal("0.001"))
    }

    @Test
    fun getPrefixStringTest() {
        assertThat(KILO.getPrefixString()).isEqualTo("k")
        assertThat(KILO.getPrefixString(true)).isEqualTo("kilo")
        assertThat(KILO.getPrefixString(true, Locale("ru","RU"))).isEqualTo("кило")
        assertThat(KILO.getPrefixString(locale = Locale("ru","RU"))).isEqualTo("к")
    }

    @Test
    fun getNominalValueTest() {
        assertThat(KILO.inNominal(1)).isEqualTo(BigDecimal(1000))
        assertThat(NOMINAL.inNominal(1)).isEqualTo(BigDecimal.ONE)
    }
}