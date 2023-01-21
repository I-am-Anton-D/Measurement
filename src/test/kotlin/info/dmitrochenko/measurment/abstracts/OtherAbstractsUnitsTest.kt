package info.dmitrochenko.measurment.abstracts
import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.quantity.Length
import info.dmitrochenko.measurment.unit.length.Inch
import info.dmitrochenko.measurment.unit.length.Meter
import info.dmitrochenko.measurment.unit.time.Day
import info.dmitrochenko.measurment.unit.time.Hour
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


internal class OtherAbstractsUnitsTest {

    @Test
    fun compositeUnitTest() {
        val day = Day
        assertThat(day.parentUnit).isEqualTo(Hour)
    }

    @Test
    fun fractionTest() {
        val fraction = Fraction("¹⁄₆₄")
        assertThat(fraction.displayString).isEqualTo("¹⁄₆₄")
    }

    @Test
    fun metricUnitTest() {
        val meter = Meter
        val dim = meter.pow(prefix = Prefix.KILO)
        assertThat(dim.getHoldersList()[0].prefix).isEqualTo(Prefix.KILO)
        assertThat(dim.getHoldersList()[0].pow).isEqualTo(1)

        val dim2 = meter.pow(pow = 3, Prefix.GIGA)
        assertThat(dim2.getHoldersList()[0].prefix).isEqualTo(Prefix.GIGA)
        assertThat(dim2.getHoldersList()[0].pow).isEqualTo(3)
    }

    @Test
    fun fractionUnitTest() {
        val inch = Inch

        assertThat(inch.getFractionString(BigDecimal("1").divide(BigDecimal("2")))).isEqualTo("½")
        assertThat(inch.getFractionString(BigDecimal("1").divide(BigDecimal("10")))).isNull()
        assertThat(inch.fractionMap).isNotEmpty

        val withDefaultRatio = object : FractionUnit<Length>() {}

        assertThat(withDefaultRatio.ratio).isEqualTo(BigDecimal.ONE)
    }

}