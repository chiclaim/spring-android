package spring.android.utils

import org.junit.Test

import org.junit.Assert.*
import java.io.File
import java.io.FileInputStream

/**
 * MD5UnitTest
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MD5UnitTest {
    @Test
    fun md5_test() {
        assertEquals(MD5.md5("MD5UnitTest"), "ea05e6758193bd5141991edda6191b4a")
    }


    @Test
    fun md5_file_test() {
        val input = FileInputStream(File("/Users/yuzhiqiang/Desktop/20076476950700000.doc"))
        assertEquals(MD5.md5(input), "46ac37d26607372e531c6bd00a68e07e")
        input.close()
    }


}