package units

interface MeasureUnit {
    fun unitSymbol():String

    fun fullUnitName():String {
        return unitSymbol()
    }
}