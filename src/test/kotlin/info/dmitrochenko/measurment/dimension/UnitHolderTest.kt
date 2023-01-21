package info.dmitrochenko.measurment.dimension

import info.dmitrochenko.measurment.exception.ConvertUnitException
import info.dmitrochenko.measurment.quantity.Length
import info.dmitrochenko.measurment.unit.length.Meter
import info.dmitrochenko.measurment.unit.length.Mile
import info.dmitrochenko.measurment.unit.mass.Gram
import info.dmitrochenko.measurment.unit.temperature.Kelvin
import info.dmitrochenko.measurment.unit.time.Hour
import info.dmitrochenko.measurment.unit.time.Second
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

internal class UnitHolderTest {

    @Test
    fun newInstanceMetricUnit() {
        val uhmetric = UnitHolder(Meter)
        assertThat(uhmetric.unit).isEqualTo(Meter)
        assertThat(uhmetric.pow).isEqualTo(1)
        assertThat(uhmetric.unitQuantity.typeName).isEqualTo(Length::class.java.name)
        assertThat(uhmetric.prefix).isEqualTo(Prefix.NOMINAL)
    }

    @Test
    fun newInstanceAbstractUnit() {
        val uh = UnitHolder(Mile)
        assertThat(uh.unit).isEqualTo(Mile)
        assertThat(uh.pow).isEqualTo(1)
        assertThat(uh.prefix).isEqualTo(Prefix.NOMINAL)
    }

    @Test
    fun newInstanceCombine() {
        val uh = UnitHolder(Meter, 3, Prefix.GIGA)
        assertThat(uh.pow).isEqualTo(3)
        assertThat(uh.prefix).isEqualTo(Prefix.GIGA)

        val mileHolder = UnitHolder(Mile, 2)
        assertThat(mileHolder.unit).isEqualTo(Mile)
        assertThat(mileHolder.prefix).isEqualTo(Prefix.NOMINAL)
        assertThat(mileHolder.pow).isEqualTo(2)
    }

    @Test
    fun copyWithTest() {
        val uh = UnitHolder(Meter, 3, Prefix.ATTO)
        var pow5 = uh.copyWith(5)
        assertThat(pow5.pow).isEqualTo(5)
        assertThat(pow5.unit).isEqualTo(uh.unit)
        assertThat(pow5.prefix).isEqualTo(uh.prefix)
        assertThat(pow5.unitQuantity).isEqualTo(uh.unitQuantity)

        pow5 = uh.copyWith(-10)
        assertThat(pow5.pow).isEqualTo(-10)
    }

    @Test
    fun invertTest() {
        val uh = UnitHolder(Mile, 3)
        var inverted = uh.invert()
        assertThat(inverted.pow).isEqualTo(-3)
        inverted = uh.copyWith(-10)
        inverted = inverted.invert()
        assertThat(inverted.pow).isEqualTo(10)
    }

    @Test
    fun canConvertTest() {
        val meter = UnitHolder(Meter)
        val mile = UnitHolder(Mile)
        assertThat(meter.canConvert(mile)).isTrue

        val second = UnitHolder(Second)
        assertThat(second.canConvert(mile)).isFalse

        val gram = UnitHolder(Gram)
        val kg = UnitHolder(Gram, prefix = Prefix.KILO)
        assertThat(gram.canConvert(kg)).isTrue

        val kelvinPow2 = UnitHolder(Kelvin, 2)
        val kelvinPow5 = UnitHolder(Kelvin, 5)
        assertThat(kelvinPow2.canConvert(kelvinPow5)).isFalse
    }

    @Test
    fun calculateRatioTest() {
        val meter = UnitHolder(Meter)
        val mile = UnitHolder(Mile)
        assertThat(meter.calculateConvertRatio(mile)).isEqualTo(BigDecimal("0.0006213711922373339696174341843633182"))

        val gram = UnitHolder(Gram)
        val kg = UnitHolder(Gram, prefix = Prefix.KILO)
        assertThat(gram.calculateConvertRatio(kg)).isEqualTo(BigDecimal("0.001"))

        val second = UnitHolder(Second)
        val hour = UnitHolder(Hour)
        assertThat(hour.calculateConvertRatio(second)).isEqualTo(BigDecimal("3600"))

        assertThrows<ConvertUnitException> {  gram.calculateConvertRatio(second) }
    }

    @Test
    fun equalsTest() {
        val meter = UnitHolder(Meter)
        val mile = UnitHolder(Mile)

        assertThat(meter == meter).isTrue
        assertThat(meter != mile).isTrue

        val kilometer = UnitHolder(Meter, prefix = Prefix.KILO)
        assertThat(meter == kilometer).isFalse

        val meterPow2 = UnitHolder(Meter, 2)
        assertThat(meterPow2 == meter).isFalse

        val newMeter = UnitHolder(Meter, 1, Prefix.NOMINAL)
        assertThat(meter == newMeter).isTrue

        val prefix = UnitHolder(Meter, 1, Prefix.KILO)
        val second = UnitHolder(Second)

        assertThat(meter.equals("Some string")).isFalse
        assertThat(meter.equals(meterPow2)).isFalse
        assertThat(meter.equals(prefix)).isFalse
        assertThat(meter.equals(second)).isFalse
        assertThat(meter.equals(mile)).isFalse

        assertThat(meter.equals(newMeter)).isTrue
    }

    @Test
    fun hashCodeTest() {
        val meter = UnitHolder(Meter)
        val mile = UnitHolder(Mile)

        assertThat(meter.hashCode()).isNotEqualTo(mile.hashCode())

        val meter2 = UnitHolder(Meter, 1, Prefix.NOMINAL)
        assertThat(meter.hashCode()).isEqualTo(meter2.hashCode())
    }

}