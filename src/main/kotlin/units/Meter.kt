package units

import java.math.BigDecimal
import java.util.*

class Meter: MeasureUnit {

    override fun fullUnitName(locale: Locale?, value: BigDecimal): String {
        val targetLocale = locale ?: Locale.getDefault()
        val five = BigDecimal(5)

        if (targetLocale.language == "ru") {
            if (value == BigDecimal.ONE) return singularForm(locale)
            if (value < five) return getBundle(locale).getString("lessFive")
            if (value >= five) return pluralForm(locale)
        }

        return if (BigDecimal.ONE == value) singularForm(locale) else pluralForm(locale)
    }
}

