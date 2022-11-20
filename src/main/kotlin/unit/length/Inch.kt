package unit.length

import measurand.Length
import measurand.meter

import unit.prototype.Fraction
import unit.prototype.FractionUnit
import unit.prototype.StreakUnit
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

object Inch : FractionUnit<Length>(0.0254), StreakUnit {

    fun fraction(numerator: Int, denominator: Int): Length {
        val fractionValue = calculateFraction(numerator, denominator)
        return fractionValue.inch()
    }

    override fun getFractionString(value: BigDecimal): String? {
        val fraction = super.getFractionString(value.stripTrailingZeros().remainder(BigDecimal.ONE)) ?: return null
        val integer = value.toBigInteger()
        return if (integer != BigInteger.ZERO) integer.toString() + fraction else fraction
    }

    //Const fraction
    val OneHalf = fraction(1, 2)
    val OneQuarter = fraction(1, 4)
    val ThreeQuarter = fraction(3, 4)
    val OneEighth = fraction(1, 8)
    val ThreeEighth = fraction(3, 8)
    val FiveEighth = fraction(5, 8)
    val SevenEighth = fraction(7, 8)
    val OneSixteenth = fraction(1, 16)

    init {
        // 64 fraction
        fractionMap[calculateFraction(1, 64)] = Fraction("¹⁄₆₄")
        fractionMap[calculateFraction(3, 64)] = Fraction("³⁄₆₄")
        fractionMap[calculateFraction(5, 64)] = Fraction("⁵⁄₆₄")
        fractionMap[calculateFraction(7, 64)] = Fraction("⁷⁄₆₄")
        fractionMap[calculateFraction(9, 64)] = Fraction("⁹⁄₆₄")
        fractionMap[calculateFraction(11, 64)] = Fraction("¹¹⁄₆₄")
        fractionMap[calculateFraction(13, 64)] = Fraction("¹³⁄₆₄")
        fractionMap[calculateFraction(15, 64)] = Fraction("¹⁵⁄₆₄")
        fractionMap[calculateFraction(17, 64)] = Fraction("¹⁷⁄₆₄")
        fractionMap[calculateFraction(19, 64)] = Fraction("¹⁹⁄₆₄")
        fractionMap[calculateFraction(21, 64)] = Fraction("²¹⁄₆₄")
        fractionMap[calculateFraction(23, 64)] = Fraction("²³⁄₆₄")
        fractionMap[calculateFraction(25, 64)] = Fraction("²⁵⁄₆₄")
        fractionMap[calculateFraction(27, 64)] = Fraction("²⁷⁄₆₄")
        fractionMap[calculateFraction(29, 64)] = Fraction("²⁹⁄₆₄")
        fractionMap[calculateFraction(31, 64)] = Fraction("³¹⁄₆₄")
        fractionMap[calculateFraction(33, 64)] = Fraction("³³⁄₆₄")
        fractionMap[calculateFraction(35, 64)] = Fraction("³⁵⁄₆₄")
        fractionMap[calculateFraction(37, 64)] = Fraction("³⁷⁄₆₄")
        fractionMap[calculateFraction(39, 64)] = Fraction("³⁷⁄₆₄")
        fractionMap[calculateFraction(41, 64)] = Fraction("³⁹⁄₆₄")
        fractionMap[calculateFraction(43, 64)] = Fraction("⁴³⁄₆₄")
        fractionMap[calculateFraction(45, 64)] = Fraction("⁴⁵⁄₆₄")
        fractionMap[calculateFraction(47, 64)] = Fraction("⁴⁷⁄₆₄")
        fractionMap[calculateFraction(49, 64)] = Fraction("⁴⁹⁄₆₄")
        fractionMap[calculateFraction(51, 64)] = Fraction("⁵¹⁄₆₄")
        fractionMap[calculateFraction(53, 64)] = Fraction("⁵³⁄₆₄")
        fractionMap[calculateFraction(55, 64)] = Fraction("⁵⁵⁄₆₄")
        fractionMap[calculateFraction(57, 64)] = Fraction("⁵⁷⁄₆₄")
        fractionMap[calculateFraction(59, 64)] = Fraction("⁵⁹⁄₆₄")
        fractionMap[calculateFraction(61, 64)] = Fraction("⁶¹⁄₆₄")
        fractionMap[calculateFraction(63, 64)] = Fraction("⁶³⁄₆₄")

        //32 fraction
        fractionMap[calculateFraction(1, 32)] = Fraction("¹⁄₃₂")
        fractionMap[calculateFraction(3, 32)] = Fraction("³⁄₃₂")
        fractionMap[calculateFraction(5, 32)] = Fraction("⁵⁄₃₂")
        fractionMap[calculateFraction(7, 32)] = Fraction("⁷⁄₃₂")
        fractionMap[calculateFraction(9, 32)] = Fraction("⁹⁄₃₂")
        fractionMap[calculateFraction(11, 32)] = Fraction("¹¹⁄₃₂")
        fractionMap[calculateFraction(13, 32)] = Fraction("¹³⁄₃₂")
        fractionMap[calculateFraction(15, 32)] = Fraction("¹⁵⁄₃₂")
        fractionMap[calculateFraction(17, 32)] = Fraction("¹⁷⁄₃₂")
        fractionMap[calculateFraction(19, 32)] = Fraction("¹⁹⁄₃₂")
        fractionMap[calculateFraction(21, 32)] = Fraction("²¹⁄₃₂")
        fractionMap[calculateFraction(23, 32)] = Fraction("²³⁄₃₂")
        fractionMap[calculateFraction(25, 32)] = Fraction("²⁵⁄₃₂")
        fractionMap[calculateFraction(27, 32)] = Fraction("²⁷⁄₃₂")
        fractionMap[calculateFraction(29, 32)] = Fraction("²⁹⁄₃₂")
        fractionMap[calculateFraction(31, 32)] = Fraction("³¹⁄₃₂")

        //16 fraction
        fractionMap[calculateFraction(1, 16)] = Fraction("¹⁄₁₆")
        fractionMap[calculateFraction(3, 16)] = Fraction("³⁄₁₆")
        fractionMap[calculateFraction(5, 16)] = Fraction("⁵⁄₁₆")
        fractionMap[calculateFraction(7, 16)] = Fraction("⁷⁄₁₆")
        fractionMap[calculateFraction(9, 16)] = Fraction("⁹⁄₁₆")
        fractionMap[calculateFraction(11, 16)] = Fraction("¹¹⁄₁₆")
        fractionMap[calculateFraction(13, 16)] = Fraction("¹³⁄₁₆")
        fractionMap[calculateFraction(15, 16)] = Fraction("¹⁵⁄₁₆")

        //8 fraction
        fractionMap[calculateFraction(1, 8)] = Fraction("⅛")
        fractionMap[calculateFraction(3, 8)] = Fraction("⅜")
        fractionMap[calculateFraction(5, 8)] = Fraction("⅝")
        fractionMap[calculateFraction(7, 8)] = Fraction("⅞")

        //4 fraction
        fractionMap[calculateFraction(1, 4)] = Fraction("¼")
        fractionMap[calculateFraction(2, 4)] = Fraction("½")
        fractionMap[calculateFraction(3, 4)] = Fraction("¾")
    }

    private fun calculateFraction(numerator: Int, denominator: Int): BigDecimal {
        return BigDecimal(numerator).divide(BigDecimal(denominator), MathContext.DECIMAL128)
    }
}

fun Number.inch() = meter(Inch)
fun Length.toInch() = valueIn(Inch)