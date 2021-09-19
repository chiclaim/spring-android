package spring.android.utils

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.Deflater
import java.util.zip.DeflaterOutputStream
import java.util.zip.Inflater
import java.util.zip.InflaterInputStream

/**
 * deflate 压缩和解压缩封装
 */
object ZLib {

    /**
     * 压缩
     *
     * @param data 待压缩数据
     * @return byte[] 压缩后的数据
     */
    fun compress(data: ByteArray): ByteArray {
        var output: ByteArray
        val compressor = Deflater()
        compressor.reset()
        compressor.setInput(data)
        compressor.finish()
        val bos = ByteArrayOutputStream(data.size)
        try {
            val buf = ByteArray(1024)
            while (!compressor.finished()) {
                val i = compressor.deflate(buf)
                bos.write(buf, 0, i)
            }
            output = bos.toByteArray()
        } catch (e: Exception) {
            output = data
            e.printStackTrace()
        } finally {
            try {
                bos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        compressor.end()
        return output
    }

    /**
     * 压缩
     *
     * @param data 待压缩数据
     * @param os   输出流
     */
    fun compress(data: ByteArray, os: OutputStream?) {
        val dos = DeflaterOutputStream(os)
        try {
            dos.write(data, 0, data.size)
            dos.finish()
            dos.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 解压缩
     *
     * @param data 待压缩的数据
     * @return byte[] 解压缩后的数据
     */
    fun decompress(data: ByteArray): ByteArray {
        var output: ByteArray
        val decompressor = Inflater()
        decompressor.reset()
        decompressor.setInput(data)
        val o = ByteArrayOutputStream(data.size)
        try {
            val buf = ByteArray(1024)
            while (!decompressor.finished()) {
                val i = decompressor.inflate(buf)
                o.write(buf, 0, i)
            }
            output = o.toByteArray()
        } catch (e: Exception) {
            output = data
            e.printStackTrace()
        } finally {
            try {
                o.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        decompressor.end()
        return output
    }

    /**
     * 解压缩
     *
     * @param is 输入流
     * @return byte[] 解压缩后的数据
     */
    fun decompress(input: InputStream?): ByteArray {
        val iis = InflaterInputStream(input)
        val o = ByteArrayOutputStream(1024)
        try {
            var i = 1024
            val buf = ByteArray(i)
            while (iis.read(buf, 0, i).also { i = it } > 0) {
                o.write(buf, 0, i)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return o.toByteArray()
    }
}