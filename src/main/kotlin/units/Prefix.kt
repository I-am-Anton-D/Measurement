package units

enum class Prefix(internationalSymbol:String, exponent:Int) {
    YOTTA("Y", 24),
    ZETTA("Z", 21),
    EXA("E", 18),
    PETA("P", 15),
    TERA("T", 12),
    GIGA("G", 9),
    MEGA("M", 6),
    KILO("k", 3),
    HECTO("h", 2),
    DEKA("da", 1),
    NOMINAL("", 0),
    DECI("d", -1),
    CENTI("c", -2),
    MILLI("m", -3),
    MICRO("μ", -6),
    NANO("n", -9),
    PICO("p", -12),
    FEMTO("f", -15),
    ATTO("a", -18),
    ZEPTO("z", -21),
    YOCTO("y", -24);

    fun toFullPrefixName() : String {
        return ""
    }
}