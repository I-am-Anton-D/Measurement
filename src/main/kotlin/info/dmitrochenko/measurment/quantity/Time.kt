package info.dmitrochenko.measurment.quantity

import info.dmitrochenko.measurment.abstracts.AbstractQuantity
import info.dmitrochenko.measurment.dimension.Dimension
import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.dimension.Prefix.NOMINAL
import info.dmitrochenko.measurment.abstracts.AbstractUnit
import info.dmitrochenko.measurment.unit.time.*
import java.math.BigDecimal

class Time(number: Number) : AbstractQuantity<Time>(number, Second) {

    constructor(number: Number, defaultToStringDimension: Dimension<Time>) : this(number) {
        this.toStringDimension = defaultToStringDimension
    }

    override fun copyWith(value: BigDecimal) = Time(value, toStringDimension)
}

fun Number.second(prefix: Prefix = NOMINAL) = Time(prefix.inNominal(this), Second.prefix(prefix))
fun Number.timeIn(dimension: Dimension<Time>) = Time(dimension.convertValue( this, Second), dimension)
fun Number.timeIn(unit: AbstractUnit<Time>) = timeIn(unit.toDimension())

fun Number.minute() = timeIn(Minute)
fun Number.hour() = timeIn(Hour)
fun Number.day() = timeIn(Day)