package units

import java.math.BigDecimal
import java.util.*

class Meter: AbstractUnit() {

    override fun expandedForm(locale: Locale, value: BigDecimal): String {
        if (locale.language == "ru") {
            return if (value == BigDecimal.ONE) singularForm(locale) else
                if (value < BigDecimal(5).abs() && value != BigDecimal.ZERO) getBundle(locale).getString("lessFive") else pluralForm(locale)
        }
        return super.expandedForm(locale, value)
    }
}

