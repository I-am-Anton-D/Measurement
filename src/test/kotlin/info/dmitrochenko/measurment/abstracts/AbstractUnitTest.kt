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
import info.dmitrochenko.measurment.unit.temperature.Celsius
import info.dmitrochenko.measurment.unit.temperature.Kelvin
import info.dmitrochenko.measurment.unit.time.Second

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
        assertThat(Meter.symbol(locale = Locale("ru", "RU"))).isEqualTo("м")
        assertThat(Gram.symbol(locale = Locale("ru", "RU"))).isEqualTo("г")
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
        assertDoesNotThrow { Meter.getBundle(Locale("TT","TT")) }
    }

    @Test
    fun foundedBundle() {
        val bundle = Mile.getBundle(Locale("ru","RU"))
        assertThat(bundle.locale).isEqualTo(Locale("ru","RU"))
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

    @Test
    fun powTest() {
        val meter = Meter
        val m1 = meter.pow()
        assertThat(m1.getHoldersList()[0].pow).isEqualTo(1)

        val m2 = meter.pow(2)
        assertThat(m2.getHoldersList()[0].pow).isEqualTo(2)
    }

    @Test
    fun timesTest() {
        val meter = Meter
        val second = Second

        val ms = meter * second

        assertThat(ms.isSingleUnit()).isFalse
        assertThat(ms.isMultiUnit()).isTrue
        assertThat(ms.getHoldersList().size).isEqualTo(2)
        assertThat(ms.getHoldersList()[0].unit).isEqualTo(Meter)
        assertThat(ms.getHoldersList()[0].pow).isEqualTo(1)
        assertThat(ms.getHoldersList()[1].unit).isEqualTo(Second)
        assertThat(ms.getHoldersList()[1].pow).isEqualTo(1)

        val mg = Meter * Gram
        val mgs = Second * mg
        assertThat(mgs.getHoldersList().size).isEqualTo(3)
        assertThat(mgs.getHoldersList()[0].unit).isEqualTo(Second)
        assertThat(mgs.getHoldersList()[0].pow).isEqualTo(1)
        assertThat(mgs.getHoldersList()[1].unit).isEqualTo(Meter)
        assertThat(mgs.getHoldersList()[1].pow).isEqualTo(1)
        assertThat(mgs.getHoldersList()[2].unit).isEqualTo(Gram)
        assertThat(mgs.getHoldersList()[2].pow).isEqualTo(1)

        val area = Meter * Meter
        assertThat(area.getHoldersList().size).isEqualTo(1)
        assertThat(area.getHoldersList()[0].unit).isEqualTo(Meter)
        assertThat(area.getHoldersList()[0].pow).isEqualTo(2)

        val volume = Meter * Meter * Meter
        assertThat(volume.getHoldersList().size).isEqualTo(1)
        assertThat(volume.getHoldersList()[0].unit).isEqualTo(Meter)
        assertThat(volume.getHoldersList()[0].pow).isEqualTo(3)
    }

    @Test
    fun divTest() {
        val velocity = Meter / Second
        assertThat(velocity.getHoldersList().size).isEqualTo(2)
        assertThat(velocity.getHoldersList()[0].unit).isEqualTo(Meter)
        assertThat(velocity.getHoldersList()[0].pow).isEqualTo(1)
        assertThat(velocity.getHoldersList()[1].unit).isEqualTo(Second)
        assertThat(velocity.getHoldersList()[1].pow).isEqualTo(-1)


        val force = Gram / velocity
        assertThat(force.getHoldersList().size).isEqualTo(3)
        assertThat(force.getHoldersList()[0].unit).isEqualTo(Gram)
        assertThat(force.getHoldersList()[0].pow).isEqualTo(1)
        assertThat(force.getHoldersList()[1].unit).isEqualTo(Meter)
        assertThat(force.getHoldersList()[1].pow).isEqualTo(-1)
        assertThat(force.getHoldersList()[2].unit).isEqualTo(Second)
        assertThat(force.getHoldersList()[2].pow).isEqualTo(1)
    }

    @Test
    fun resolveZeroOffsetTest() {
        val kelvin = Kelvin
        val celsius = Celsius

        assertThat(Kelvin.resolveZeroOffset(kelvin, celsius)).isEqualTo(BigDecimal("-273.15"))
    }

    @Test
    fun toStringTestLocale() {
        assertThat(Meter.toString(locale = Locale("ru", "RU"))).isEqualTo("м")
    }

}