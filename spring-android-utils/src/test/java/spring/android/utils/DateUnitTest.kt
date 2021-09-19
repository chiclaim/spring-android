package spring.android.utils

import org.junit.Assert
import org.junit.Test

class DateUnitTest {

    @Test
    fun format(){
        Assert.assertEquals(DateUtil.format("yyyy-MM", System.currentTimeMillis()), "2021-09")
    }

    @Test
    fun parse(){
       Assert.assertEquals(DateUtil.parse("yyyy/MM/dd","2021/12/12"), 1639238400000)
    }

}