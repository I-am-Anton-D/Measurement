package info.dmitrochenko.measurment.dimension

import info.dmitrochenko.measurment.exception.ConvertDimensionException
import info.dmitrochenko.measurment.exception.NotSingleUnitDimensionException
import info.dmitrochenko.measurment.quantity.Length
import info.dmitrochenko.measurment.quantity.Quantity
import info.dmitrochenko.measurment.quantity.Temperature
import info.dmitrochenko.measurment.quantity.Velocity
import info.dmitrochenko.measurment.unit.length.Meter
import info.dmitrochenko.measurment.unit.length.Mile
import info.dmitrochenko.measurment.unit.mass.Gram
import info.dmitrochenko.measurment.unit.temperature.Celsius
import info.dmitrochenko.measurment.unit.temperature.Fahrenheit
import info.dmitrochenko.measurment.unit.temperature.Kelvin
import info.dmitrochenko.measurment.unit.time.Hour
import info.dmitrochenko.measurment.unit.time.Second
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.util.*

internal class DimensionTest {

    @Test
    fun newInstance() {
        val dim = Dimension<Quantity>(Meter.pow(2), Second.pow(3), Gram.toDimension())

        assertThat(dim.isMultiUnit()).isTrue
        assertThat(dim.isSingleUnit()).isFalse
        assertThrows<NotSingleUnitDimensionException> { dim.getSingleUnit() }

        val newDim = dim / Gram / Second.pow(3)

        assertThat(newDim.isMultiUnit()).isFalse
        assertThat(newDim.isSingleUnit()).isTrue

        assertThat(newDim.getSingleUnit()).isEqualTo(Meter)
    }

    @Test
    fun convertValueTest() {
        val meterDimension = Dimension<Length>(Meter)
        val mileDimension = Dimension<Length>(Mile)
        val msec = Velocity.msec()
        val mph = Velocity.mph()

        assertThat(meterDimension.convertValue(1609.344, Mile)).isEqualTo(BigDecimal("1.000000000000000"))

        val dim1 = Dimension<Quantity>(Second, Gram)
        val dim2 = Dimension<Quantity>(Meter)
        val dim3 = Dimension<Quantity>(Meter, Hour)

        assertThrows<ConvertDimensionException> { dim1.convertValue(1, dim2)}
        assertThrows<ConvertDimensionException> { dim1.convertValue(1, dim3)}

        assertDoesNotThrow { msec.convertValue(10, mph)}
    }

    @Test
    fun moveZeroTest() {
        val fahrenheit = Dimension<Temperature>(Fahrenheit)
        val celsius = Dimension<Temperature>(Celsius)
        val kelvin = Dimension<Temperature>(Kelvin)

        //!!!! Only for moveZero test, wrong values on convert !!!!
        assertThat(fahrenheit.convertValue(1, Kelvin)).isEqualTo(BigDecimal("255.927777752185"))
        assertThat(kelvin.convertValue(0, Celsius)).isEqualTo(BigDecimal("-273.15"))
        assertThat(fahrenheit.convertValue(0, Celsius)).isEqualTo(BigDecimal("-103.622222211860"))
        assertThat(kelvin.convertValue(0, Fahrenheit)).isEqualTo(BigDecimal("-459.6700000000000"))
        assertThat(celsius.convertValue(0, Fahrenheit)).isEqualTo(BigDecimal("-335.7360000335736"))
    }

    @Test
    fun toStringTest() {
        val msec = Velocity.msec()
        Locale.setDefault(Locale("ru", "RU"))
        assertThat(msec.toString()).isEqualTo("м/c")
        assertThat(msec.toString(locale = Locale("en", "GB"))).isEqualTo("m/s")

        val dim = Dimension<Quantity>(Meter.toDimension(), Second.toDimension(), Gram.pow(-1), Kelvin.pow(-1))
        assertThat(dim.toString()).isEqualTo("м·c/г·К")

        val hz = Dimension<Quantity>(Second.pow(-1))
        assertThat(hz.toString()).isEqualTo("1/c")

        val empty = Dimension<Quantity>(Meter.pow(1), Meter.pow(-1))
        assertThat(empty.toString()).isEqualTo("")

        val dim2 = Dimension<Quantity>(Second.toDimension(), Gram.pow(-1), Kelvin.pow(-1))
        assertThat(dim2.toString()).isEqualTo("c/г·К")

        val dim3 = Dimension<Quantity>(Meter.toDimension(), Second.toDimension(), Gram.pow(-1))
        assertThat(dim3.toString()).isEqualTo("м·c/г")


        val dim4 = Dimension<Quantity>(Gram.pow(-1), Kelvin.pow(-1))
        assertThat(dim4.toString()).isEqualTo("1/г·К")

        val dim5 = Dimension<Quantity>(Meter.toDimension(), Second.toDimension())
        assertThat(dim5.toString()).isEqualTo("м·c")

        val dim6 = Dimension<Quantity>(Meter.toDimension(), Second.toDimension(), Gram.pow(-1))
        assertThat(dim6.toString()).isEqualTo("м·c/г")
    }

    @Test
    fun equalsTest() {
        val msec = Velocity.msec()

        assertThat(msec.equals(msec)).isTrue
        assertThat(msec.equals("false")).isFalse
    }

    @Test
    fun staticTest() {
        assertThat(Dimension.powInSuperScript).isInstanceOf(List::class.java)

    }
}