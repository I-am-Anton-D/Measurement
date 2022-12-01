package unit.length

import measurand.Length
import dimension.Prefix
import unit.abstract.MetricUnit

object Meter : MetricUnit<Length>() {

    val KILO = Meter.prefix(Prefix.KILO)
    val NANO = Meter.prefix(Prefix.NANO)
}

