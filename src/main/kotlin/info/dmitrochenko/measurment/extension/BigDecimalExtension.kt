package info.dmitrochenko.measurment.extension

import java.math.BigDecimal

fun BigDecimal.out(): String = this.stripTrailingZeros().toPlainString()

fun Number.toBigDecimal(): BigDecimal = BigDecimal(this.toString())