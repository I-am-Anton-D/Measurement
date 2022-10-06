package units

import measurand.Length
import java.math.BigDecimal
import java.math.MathContext
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class Meter : AbstractUnit<Length>() {

    override fun expandedForm(locale: Locale, value: BigDecimal): String {
        if (locale.language == "ru") {
            return if (value == BigDecimal.ONE) singularForm(locale) else
                if (value < BigDecimal(5).abs() && value != BigDecimal.ZERO) getBundle(locale).getString("lessFive")
                else pluralForm(locale)
        }
        return super.expandedForm(locale, value)
    }
}

class Mile : AbstractUnit<Length>() {
    override val ratio = 1609.344
}
