package spring.android.utils

import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode


class NumberUnitTest {

    @Test
    fun precisionTest() {
        Assert.assertEquals("${1.2 * 3}", "3.6")
    }

    @Test
    fun multiplyTest() {
        Assert.assertEquals(NumberUtils.multiply(1.2, 3), "3.6")
        Assert.assertEquals(NumberUtils.multiply2(1.2, 3).toDouble().toString(), "3.6")
        Assert.assertEquals(NumberUtils.multiply(1.2, 3.0), "3.60")
        Assert.assertEquals(NumberUtils.multiply2(1.2, 3.0).toDouble().toString(), "3.6")
        Assert.assertEquals(NumberUtils.multiply(1, 3.0), "3.0")
        Assert.assertEquals(NumberUtils.multiply(1, 3), "3")
        Assert.assertEquals(NumberUtils.multiply2(1, 3).toInt(), 3)
    }

    @Test
    fun divideTest() {
        Assert.assertEquals(NumberUtils.divide(1, 3, 5, RoundingMode.UP), "0.33334")
        Assert.assertEquals(
            NumberUtils.divide2(1, 3, 5, RoundingMode.UP).toDouble().toString(),
            "0.33334"
        )
    }

    @Test
    fun addTest() {
        Assert.assertEquals(NumberUtils.add(1, 3), "4")
        Assert.assertEquals(NumberUtils.add(1.1, 3.9), "5.0")
        Assert.assertEquals(
            NumberUtils.add2(1.1, 3.9999).setScale(2, RoundingMode.DOWN).toString(),
            "5.09"
        )
    }

    @Test
    fun subtractTest() {
        Assert.assertEquals(NumberUtils.subtract(12.9, 12), "0.9")
        Assert.assertEquals(NumberUtils.subtract2(12.9, 12).toDouble().toString(), "0.9")
    }


    @Test
    fun fen2yuan() {
        Assert.assertEquals(NumberUtils.fen2yuan(10), "0.10")
    }

    @Test
    fun getScaleNumberTest() {
        val bigDecimal = BigDecimal("12.2289")
        Assert.assertEquals(NumberUtils.getScaleNumber(bigDecimal, 2, RoundingMode.DOWN), "12.22")
    }

    @Test
    fun roundModeHalfUpTest() {

        // 四舍五入
        Assert.assertEquals(
            BigDecimal(12.5055.toString()).setScale(3, RoundingMode.HALF_UP).toString(),
            "12.506"
        )

        // 五舍六入 1.5->1   1.6->1   -1.5->-1   -1.6->-2   1.15->1.1   1.16->1.2   1.55->1.6   1.56->1.6
        Assert.assertEquals(
            BigDecimal(12.506.toString()).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString(),
            "12.51"
        )
        println(BigDecimal(12.54.toString()).setScale(1, BigDecimal.ROUND_HALF_DOWN).toString())
        println(BigDecimal(12.15.toString()).setScale(1, BigDecimal.ROUND_HALF_DOWN).toString())
        println(BigDecimal(4.16.toString()).setScale(1, BigDecimal.ROUND_HALF_DOWN).toString())
        println(BigDecimal(2.15.toString()).setScale(1, BigDecimal.ROUND_HALF_DOWN).toString())

        // 如果是 5，则前一位变偶数1.15->1.2   1.16->1.2   1.25->1.2   1.26->1.3
        Assert.assertEquals(
            BigDecimal(12.525.toString()).setScale(2, RoundingMode.HALF_EVEN).toString(),
            "12.52"
        )


        // [往地板方向，数字肯定是越来越小]与 CEILING 相反，往负无穷   1.1->1   1.5->1   1.8->1   -1.1->-2   -1.5->-2   -1.8->-2
        Assert.assertEquals(
            BigDecimal(12.505.toString()).setScale(2, BigDecimal.ROUND_FLOOR).toString(),
            "12.50"
        )

        // [往天花板方向，数字肯定越来越大]舍位时往正无穷方向移动   1.1->2   1.5->2   1.8->2   -1.1->-1   -1.5->-1   -1.8->-1
        Assert.assertEquals(
            BigDecimal(12.501.toString()).setScale(2, BigDecimal.ROUND_CEILING).toString(),
            "12.51"
        )

        // 向 0 的方向移动 1.1->1   1.5->1   1.8->1   -1.1->-1   -1.5->-1   -1.8>-1
        Assert.assertEquals(
            BigDecimal(12.509.toString()).setScale(2, BigDecimal.ROUND_DOWN).toString(),
            "12.50"
        )

        // 与 ROUND_DOWN 相反，向远离 0 的方向移动 1.1->2   1.5->2   1.8->2   -1.1->-2   -1.5->-2   -1.8->-2
        Assert.assertEquals(
            BigDecimal(12.505.toString()).setScale(2, BigDecimal.ROUND_UP).toString(), "12.51"
        )

    }


}