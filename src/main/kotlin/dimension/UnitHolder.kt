package dimension

import unit.Prefix
import unit.prototype.AbstractUnit
import unit.prototype.MetricUnit

class UnitHolder(val unit: AbstractUnit<*>, var pow: Int = 1) {
    var prefix: Prefix = Prefix.NOMINAL
        private set

    constructor(unit: MetricUnit<*>, pow:Int, prefix: Prefix) : this(unit, pow) {
        this.prefix = prefix
    }

    fun inverse() : UnitHolder  {
        return if (this.unit is MetricUnit) {
            UnitHolder(unit, -pow, prefix)
        } else {
            UnitHolder(unit, -pow)
        }
    }

    operator fun times(other: AbstractUnit<*>) = Dimension(this, UnitHolder(other))

    operator fun times(other: UnitHolder) = Dimension(this, other)

    operator fun div(other: AbstractUnit<*>) = Dimension(this, UnitHolder(other, -1))

    operator fun div(other: UnitHolder) = Dimension(this, other.inverse())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnitHolder

        if (pow != other.pow) return false
        if (prefix != other.prefix) return false
        if (unit != other.unit) return false

        return true
    }

    override fun hashCode(): Int {
        return unit.hashCode()
    }
}