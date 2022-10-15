package unit

import measurand.Length
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class AbstractUnitTest {

    @Test
    fun createDescendant() {
        assertDoesNotThrow {
            val descendant = object : AbstractUnit<Length>() {}
        }
    }
}