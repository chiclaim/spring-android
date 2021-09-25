package spring.android.utils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 数字相关的工具类
 *
 * 在开发中要格外注意金钱相关的计算，保留数字的精度要使用 [BigDecimal]，本工具类封装了开发中常用的 [BigDecimal] 操作
 *
 * 如果使用 Kotlin 开发，也可以使用基本类型及String类型的扩展函数来实现 BigDecimal 计算，如：
 *
 * ```
 * val num = 1.0
 * num.toBigDecimal()
 *
 * val str = "1.0"
 * str.toBigDecimal()
 * ```
 *
 */
object NumberUtils {


    /**
     * 整形数字相除，例如货币单位分转元 [fen2yuan]
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @param scale 保留的精度，默认保留 2 位
     * @return string
     */
    fun divide(
        dividend: Any,
        divisor: Any,
        scale: Int = 2,
        roundingMode: RoundingMode = RoundingMode.DOWN
    ): String {
        return divide2(dividend, divisor, scale, roundingMode).toString()
    }

    fun divide2(
        dividend: Any,
        divisor: Any,
        scale: Int = 2,
        roundingMode: RoundingMode = RoundingMode.DOWN
    ): BigDecimal {
        return BigDecimal(dividend.toString())
            .divide(BigDecimal(divisor.toString()), scale, roundingMode)
    }


    /**
     * 货币单位分转元
     */
    fun fen2yuan(fen: Long): String {
        return divide(fen, 100)
    }

    /**
     * 封装乘法操作
     */
    fun multiply(num1: Any, num2: Any): String {
        return multiply2(num1, num2).toString()
    }

    /**
     * 封装乘法操作
     */
    fun multiply2(num1: Any, num2: Any): BigDecimal {
        return BigDecimal(num1.toString())
            .multiply(BigDecimal(num2.toString()))
    }

    /**
     * 封装加法操作
     */
    fun add(num1: Any, num2: Any): String {
        return add2(num1, num2).toString()
    }

    /**
     * 封装加法操作
     */
    fun add2(num1: Any, num2: Any): BigDecimal {
        return BigDecimal(num1.toString())
            .add(BigDecimal(num2.toString()))
    }


    /**
     * 封装减法操作
     */
    fun subtract(num1: Any, num2: Any): String {
        return subtract2(num1, num2).toString()
    }

    /**
     * 封装减法操作
     */
    fun subtract2(num1: Any, num2: Any): BigDecimal {
        return BigDecimal(num1.toString())
            .subtract(BigDecimal(num2.toString()))
    }


    /**
     * 获取指定精度的 BigDecimal 值
     * @param scale 保留的小数点个数, 默认保留2位
     * @param roundingMode 精度模式 [RoundingMode] 默认模式为 RoundingMode.DOWN
     */
    fun getScaleNumber(
        number: BigDecimal,
        scale: Int = 2,
        roundingMode: RoundingMode = RoundingMode.DOWN
    ): String {
        return number.setScale(scale, roundingMode).toString()
    }


}