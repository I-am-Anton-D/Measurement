package info.dmitrochenko.measurment.abstracts

import info.dmitrochenko.measurment.exception.NoBundleForAnonymousClassException

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import info.dmitrochenko.measurment.quantity.Length
import info.dmitrochenko.measurment.unit.length.Meter
import info.dmitrochenko.measurment.unit.length.Mile
import info.dmitrochenko.measurment.unit.mass.Gram

import java.math.BigDecimal
import java.util.*

internal class AbstractUnitTest {

    @Test
    fun createDescendant() {
        assertDoesNotThrow {
            object : AbstractUnit<Length>() {}
        }
    }

    @Test
    fun ratioEqualsOneWithoutParse() {
        val descendant = object : AbstractUnit<Length>() {}
        assertThat(descendant.ratio).isEqualTo(BigDecimal.ONE)
    }

    @Test
    fun ratioEqualsSetNumberValue() {
        val descendant = object : AbstractUnit<Length>(5.4321) {}
        assertThat(descendant.ratio).isEqualTo(BigDecimal(5.4321.toString()))
    }

    @Test
    fun ratioEqualsSetBigDecimalValue() {
        val descendant = object : AbstractUnit<Length>(BigDecimal(5.4321.toString())) {}
        assertThat(descendant.ratio).isEqualTo(BigDecimal(5.4321.toString()))
    }


    @Test
    fun getLocalizedStringForAnonymousClass() {
        val descendant = object : AbstractUnit<Length>() {}

        assertThrows<NoBundleForAnonymousClassException> {
           descendant.symbol()
        }

        assertThrows<NoBundleForAnonymousClassException> {
            descendant.symbol(Locale("en","GB"))
        }

        assertThrows<NoBundleForAnonymousClassException> {
            descendant.name()
        }

    }

    @Test fun overrideGetSymbol() {
        val descendant = object : AbstractUnit<Length>() {
            override fun symbol(locale: Locale): String {
                return "X"
            }
        }
        assertThat(descendant.symbol()).isEqualTo("X")
        assertThat(descendant.symbol(locale = Locale("XX","XX"))).isEqualTo("X")
    }

    @Test
    fun getSymbolOnExistUnit() {
        assertThat(Meter.symbol(locale = Locale("en", "GB"))).isEqualTo("m")
        assertThat(Gram.symbol(locale = Locale("en", "GB"))).isEqualTo("g")
    }

    @Test
    fun getSymbolOfDefaultLocal() {
        Locale.setDefault(Locale("en", "GB"))
        assertThat(Meter.symbol()).isEqualTo("m")
        assertThat(Gram.symbol()).isEqualTo("g")
    }

    @Test
    fun getSingularForm() {
        assertThat(Meter.name(locale = Locale("en","GB"))).isEqualTo("meter")
        Locale.setDefault(Locale("en", "GB"))
        assertThat(Meter.name()).isEqualTo("meter")
    }

    @Test
    fun overrideSingularForm() {
        val singularForm = "SINGLE"
        val descendant = object : AbstractUnit<Length>() {
            override fun name(locale: Locale): String {
                return singularForm
            }
        }
        assertThat(descendant.name()).isEqualTo(singularForm)
    }

    @Test
    fun overridePluralForm() {
        val pluralForm = "PLURAL"
        val descendant = object : AbstractUnit<Length>() {
            override fun name(locale: Locale): String {
                return pluralForm
            }
        }
        assertThat(descendant.name()).isEqualTo(pluralForm)
    }




    @Test
    fun toStringTest() {
        Locale.setDefault(Locale("en","GB"))
        assertThat(Meter.toString()).isEqualTo("m")
    }


    @Test
    fun noBundleForAnonymousClass() {
        val descendant = object : AbstractUnit<Length>() {}
        assertThrows<NoBundleForAnonymousClassException> {
            descendant.getBundle(locale = Locale.getDefault())
        }
    }

    @Test
    fun notFoundBundle() {
        val bundle = Meter.getBundle(Locale("TT","TT"))
        assertThat(bundle.locale).isEqualTo(Locale.getDefault())
    }

    @Test
    fun foundedBundle() {
        val bundle = Mile.getBundle(Locale("en","GB"))
        assertThat(bundle.locale).isEqualTo(Locale("en","GB"))
    }

    object SomeUnit: AbstractUnit<Length>(1.234)

    @Test
    fun testNewUnit() {
        assertThrows<MissingResourceException>{
            SomeUnit.getBundle(Locale.getDefault())
        }

        assertThrows<MissingResourceException>{
            SomeUnit.symbol()
        }
        assertThat(SomeUnit.ratio).isEqualTo(BigDecimal(1.234.toString()))
    }

}