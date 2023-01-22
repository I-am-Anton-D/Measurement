package info.dmitrochenko.measurment.extension

import info.dmitrochenko.measurment.exception.DecompositionException
import info.dmitrochenko.measurment.exception.FractionalTransformException
import info.dmitrochenko.measurment.quantity.Velocity
import info.dmitrochenko.measurment.quantity.meter
import info.dmitrochenko.measurment.quantity.msec
import info.dmitrochenko.measurment.unit.length.Inch
import info.dmitrochenko.measurment.unit.length.foot
import info.dmitrochenko.measurment.unit.length.inch
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

internal class ExtensionTest {

    @Test
    fun bigDecimalExt() {
        val b = 10.toBigDecimal()
        assertThat(b).isInstanceOf(BigDecimal::class.java)
        assertThat(b).isEqualTo(BigDecimal("10"))

        assertThat(b.out()).isEqualTo("10")
    }

    @Test
    fun dimExt() {
        val d = Velocity.msec()

        Locale.setDefault(Locale("ru", "RU"))
        assertThat(d.toAnsiString()).isEqualTo("м c^-1")
    }

    @Test
    fun quantityToAnsiStringTest() {
        val velocity = 10.msec()
        Locale.setDefault(Locale("ru", "RU"))


        assertThat(velocity.toAnsiString()).isEqualTo("10 m s^-1")
        assertThat(velocity.toAnsiString(Velocity.mph())).isEqualTo("22.36936292054402 miles h^-1")
        assertThat(velocity.toAnsiString(Velocity.msec(), valueFormat = DecimalFormat("#.#"))).isEqualTo("10 m s^-1")
        assertThat(
            velocity.toAnsiString(
                Velocity.msec(),
                valueFormat = DecimalFormat("#.#"),
                locale = Locale("ru", "RU")
            )
        ).isEqualTo("10 м c^-1")
    }

    @Test
    fun decomposedTest() {
        val velocity = 10.msec()
        assertThrows<DecompositionException> { velocity.toDecomposedString() }

        val length = 1.meter()
        assertThrows<DecompositionException> { length.toDecomposedString() }

//        val d = 1.2.day()
//        Locale.setDefault(Locale("ru", "RU"))
//
//        assertThat(d.toDecomposedString()).isEqualTo("1 д")
//        assertThat(d.toDecomposedString(Minute)).isEqualTo("1 д")
    }

    @Test
    fun fractionalTest() {
        val inch = 10.inch()
        val thee8 = Inch.ThreeEighth
        val inchWithHalf = 1.5.inch()
        val feet = 1.5.foot()
        assertThrows<FractionalTransformException> { feet.toFractionalString() }

        val velocity = 10.msec()
        assertThrows<FractionalTransformException> { velocity.toFractionalString() }

        Locale.setDefault(Locale("ru", "RU"))
        assertThat(inch.toFractionalString()).isEqualTo("10 дюйм")
        assertThat(inchWithHalf.toFractionalString()).isEqualTo("1½ дюйм")
        assertThat(thee8.toFractionalString()).isEqualTo("⅜ дюйм")
    }

}