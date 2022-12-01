package unit

import exception.NoBundleForAnonymousClass
import quantity.Length
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import unit.abstract.AbstractUnit
import unit.length.Meter
import unit.length.Mile
import unit.mass.Gram
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

        assertThrows<NoBundleForAnonymousClass> {
           descendant.symbol()
        }

        assertThrows<NoBundleForAnonymousClass> {
            descendant.symbol(Locale("en","GB"))
        }

        assertThrows<NoBundleForAnonymousClass> {
            descendant.singularForm()
        }
        assertThrows<NoBundleForAnonymousClass> {
            descendant.pluralForm()
        }
        assertThrows<NoBundleForAnonymousClass> {
            descendant.expandedForm(value = BigDecimal.ONE)
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
        assertThat(Meter.singularForm(locale = Locale("en","GB"))).isEqualTo("meter")
        Locale.setDefault(Locale("en", "GB"))
        assertThat(Meter.singularForm()).isEqualTo("meter")
    }

    @Test
    fun overrideSingularForm() {
        val singularForm = "SINGLE"
        val descendant = object : AbstractUnit<Length>() {
            override fun singularForm(locale: Locale): String {
                return singularForm
            }
        }
        assertThat(descendant.singularForm()).isEqualTo(singularForm)
    }

    @Test
    fun getPluralForm() {
        assertThat(Meter.pluralForm(locale = Locale("en","GB"))).isEqualTo("meters")
        Locale.setDefault(Locale("en", "GB"))
        assertThat(Meter.pluralForm()).isEqualTo("meters")
    }

    @Test
    fun overridePluralForm() {
        val pluralForm = "PLURAL"
        val descendant = object : AbstractUnit<Length>() {
            override fun singularForm(locale: Locale): String {
                return pluralForm
            }
        }
        assertThat(descendant.singularForm()).isEqualTo(pluralForm)
    }

    @Test
    fun getExpandForm() {
        assertThat(Meter.expandedForm(locale = Locale("en","GB"), BigDecimal.ONE)).isEqualTo("meter")
        assertThat(Meter.expandedForm(locale = Locale("en","GB"), BigDecimal.TEN)).isEqualTo("meters")
        assertThat(Meter.expandedForm(locale = Locale("en","GB"), BigDecimal(123))).isEqualTo("meters")
        Locale.setDefault(Locale("en", "GB"))
        assertThat(Meter.expandedForm(value = BigDecimal(123))).isEqualTo("meters")
    }

    @Test
    fun checkRuLocalizationForExpandedForm() {
        Locale.setDefault(Locale("ru", "RU"))
        assertThat(Meter.expandedForm(value = BigDecimal.ZERO)).isEqualTo("метров")
        assertThat(Meter.expandedForm(value = BigDecimal.ONE)).isEqualTo("метр")
        assertThat(Meter.expandedForm(value =  BigDecimal(2))).isEqualTo("метра")
        assertThat(Meter.expandedForm(value =  BigDecimal(3))).isEqualTo("метра")
        assertThat(Meter.expandedForm(value =  BigDecimal(4))).isEqualTo("метра")
        assertThat(Meter.expandedForm(value =  BigDecimal(5))).isEqualTo("метров")
        assertThat(Meter.expandedForm(value =  BigDecimal(50))).isEqualTo("метров")
    }

    @Test
    fun checkValueInBaseUnit() {
        assertThat(Meter.valueToBaseUnit(1)).isEqualTo(BigDecimal.ONE)
        assertThat(Mile.valueToBaseUnit(1)).isEqualTo(Mile.ratio)
        assertThat(Mile.valueToBaseUnit(10)).isEqualTo(Mile.ratio.multiply(BigDecimal.TEN))
        assertThat(Mile.valueToBaseUnit(1)).isEqualTo(BigDecimal(1609.344.toString()))
    }

    @Test
    fun overrideValueInBaseUnit() {
        val descendant = object : AbstractUnit<Length>() {
            override fun valueToBaseUnit(number: Number): BigDecimal {
                return BigDecimal.ZERO
            }
        }
        assertThat(descendant.valueToBaseUnit(10)).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun valueOfDescendant() {
        val descendant = object : AbstractUnit<Length>() {}

        assertThat(descendant.valueToBaseUnit(1)).isEqualTo(BigDecimal.ONE)
        assertThat(descendant.valueToBaseUnit(10)).isEqualTo(BigDecimal.TEN)
    }

    @Test
    fun toStringTest() {
        Locale.setDefault(Locale("en","GB"))
        assertThat(Meter.toString()).isEqualTo("m")
    }

    @Test
    fun toStringTestWithParameters() {
        Locale.setDefault(Locale("en","GB"))

        assertThat(Meter.toString(expand = true, value = BigDecimal.ZERO)).isEqualTo("meters")
        assertThat(Meter.toString(expand = true, value = BigDecimal.ONE)).isEqualTo("meter")
        assertThat(Meter.toString(expand = true, value = BigDecimal.TEN)).isEqualTo("meters")
        assertThat(Meter.toString(expand = false, value = BigDecimal.TEN)).isEqualTo("m")
        assertThat(Meter.toString(expand = false, value = BigDecimal.ONE)).isEqualTo("m")
        assertThat(Meter.toString(value = BigDecimal.ONE)).isEqualTo("m")

        assertThat(Meter.toString(expand = true, locale = Locale("ru", "RU"), value = BigDecimal.ONE)).isEqualTo("метр")
        assertThat(Meter.toString(expand = true, locale = Locale("ru", "RU"), value = BigDecimal.TEN)).isEqualTo("метров")
        assertThat(Meter.toString(expand = true, locale = Locale("ru", "RU"), value = BigDecimal.ZERO)).isEqualTo("метров")
        assertThat(Meter.toString(expand = false, locale = Locale("ru", "RU"), value = BigDecimal.ZERO)).isEqualTo("м")
        assertThat(Meter.toString(expand = false, locale = Locale("ru", "RU"), value = BigDecimal.ONE)).isEqualTo("м")
        assertThat(Meter.toString(locale = Locale("ru", "RU"), value = BigDecimal.ONE)).isEqualTo("м")
    }

    @Test
    fun overrideToStringTestWithParameters() {
        val descendant = object : AbstractUnit<Length>() {
            override fun toString(expand: Boolean, locale: Locale, value: BigDecimal): String {
                return "X"
            }
        }
        assertThat(descendant.toString(expand = true, value = BigDecimal.ZERO)).isEqualTo("X")
        assertThat(descendant.toString(expand = true, value = BigDecimal.ONE)).isEqualTo("X")
        assertThat(descendant.toString(expand = true, value = BigDecimal.TEN)).isEqualTo("X")
        assertThat(descendant.toString(expand = false, locale = Locale("ru", "RU"), value = BigDecimal.ZERO)).isEqualTo("X")
    }

    @Test
    fun noBundleForAnonymousClass() {
        val descendant = object : AbstractUnit<Length>() {}
        assertThrows<NoBundleForAnonymousClass> {
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

        assertThrows<MissingResourceException>{
            SomeUnit.pluralForm()
        }

        assertThrows<MissingResourceException>{
            SomeUnit.pluralForm()
        }

        assertThat(SomeUnit.ratio).isEqualTo(BigDecimal(1.234.toString()))
    }

}