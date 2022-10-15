package unit

import measurand.Length

object Meter : MetricUnit<Length>()
object Mile : AbstractUnit<Length>(1609.344)
