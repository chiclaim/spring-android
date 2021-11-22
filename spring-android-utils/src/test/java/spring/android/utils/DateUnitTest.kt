package spring.android.utils

import org.junit.Assert
import org.junit.Test

class DateUnitTest {

    @Test
    fun test(){
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        // it will throw : java.lang.NumberFormatException: multiple points
        for (i in 0..10){
            Thread{
                sdf.parse("2021-11-22 12:23:18").also {
                    println(it)
                }
            }.start()
        }
    }

    @Test
    fun format(){
        Assert.assertEquals(DateUtil.format("yyyy-MM", System.currentTimeMillis()), "2021-09")
    }

    @Test
    fun parse(){
       Assert.assertEquals(DateUtil.parse("yyyy/MM/dd","2021/12/12"), 1639238400000)
    }

}