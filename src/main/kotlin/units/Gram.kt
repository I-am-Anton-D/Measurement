package units

import java.math.BigDecimal
import java.util.*

class Gram: AbstractUnit() {
    override fun expandedForm(locale: Locale, value: BigDecimal): String {
        if (locale.language == "ru") {
            if (value == BigDecimal.ONE) return singularForm(locale)

            val five = BigDecimal(5)
            if (value < five) return getBundle(locale).getString("lessFive")
            if (value >= five) return pluralForm(locale)
        }

        return super.expandedForm(locale, value)
    }
}