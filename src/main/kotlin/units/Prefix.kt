public enum MetricPrefix implements Prefix {
    /** Prefix for 10<sup>24</sup>. */
    YOTTA("Y", 24),
    /** Prefix for 10<sup>21</sup>. */
    ZETTA("Z", 21),
    /** Prefix for 10<sup>18</sup>. */
    EXA("E", 18),
    /** Prefix for 10<sup>15</sup>. */
    PETA("P", 15),
    /** Prefix for 10<sup>12</sup>. */
    TERA("T", 12),
    /** Prefix for 10<sup>9</sup>. */
    GIGA("G", 9),
    /** Prefix for 10<sup>6</sup>. */
    MEGA("M", 6),
    /** Prefix for 10<sup>3</sup>. */
    KILO("k", 3),
    /** Prefix for 10<sup>2</sup>. */
    HECTO("h", 2),
    /** Prefix for 10<sup>1</sup>. */
    DEKA("da", 1),
    /** Prefix for 10<sup>-1</sup>. */
    DECI("d", -1),
    /** Prefix for 10<sup>-2</sup>. */
    CENTI("c", -2),
    /** Prefix for 10<sup>-3</sup>. */
    MILLI("m", -3),
    /** Prefix for 10<sup>-6</sup>. */
    MICRO("\u00b5", -6),
    /** Prefix for 10<sup>-9</sup>. */
    NANO("n", -9),
    /** Prefix for 10<sup>-12</sup>. */
    PICO("p", -12),
    /** Prefix for 10<sup>-15</sup>. */
    FEMTO("f", -15),
    /** Prefix for 10<sup>-18</sup>. */
    ATTO("a", -18),
    /** Prefix for 10<sup>-21</sup>. */
    ZEPTO("z", -21),
    /** Prefix for 10<sup>-24</sup>. */
    YOCTO("y", -24);

    /**
     * The symbol of this prefix, as returned by {@link #getSymbol}.
     *
     * @serial
     * @see #getSymbol()
     */
    private final String symbol;

    /**
     * Exponent part of the associated factor in base^exponent representation.
     */
    private final int exponent;

    /**
     * Creates a new prefix.
     *
     * @param symbol
     *          the symbol of this prefix.
     * @param exponent
     *          part of the associated factor in base^exponent representation.
     */
    private MetricPrefix (String symbol, int exponent) {
        this.symbol = symbol;
        this.exponent = exponent;
    }
}