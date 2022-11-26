package unit.length

import measurand.Length
import unit.Prefix
import unit.prototype.MetricUnit

object Meter : MetricUnit<Length>() {

    val KILO = Meter.prefix(Prefix.KILO)
    val NANO = Meter.prefix(Prefix.NANO)
}

