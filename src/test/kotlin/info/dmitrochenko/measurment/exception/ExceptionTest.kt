package info.dmitrochenko.measurment.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class ExceptionTest {

    @Test
    fun defaultMessageInException() {
        val ex1 = ConvertDimensionException()
        assertThat(ex1.message).isEqualTo("Can not convert dimension")

        val ex2 = ConvertUnitException()
        assertThat(ex2.message).isEqualTo("Can not convert unit")

        val ex3 = DecompositionException()
        assertThat(ex3.message).isEqualTo("Can not decompose unit")

        val ex4 = FractionalTransformException()
        assertThat(ex4.message).isEqualTo("Can not create fractional string")
    }
}