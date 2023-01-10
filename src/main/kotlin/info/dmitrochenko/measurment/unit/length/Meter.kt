package info.dmitrochenko.measurment.unit.length


import info.dmitrochenko.measurment.dimension.Prefix
import info.dmitrochenko.measurment.abstracts.MetricUnit
import info.dmitrochenko.measurment.quantity.Length

object Meter : MetricUnit<Length>() {

    val KILO = Meter.prefix(Prefix.KILO)
    val NANO = Meter.prefix(Prefix.NANO)
}

