package com.murgupluoglu.kotlinmvvm

import android.content.Context
import java.io.BufferedReader
import java.io.InputStream

/**
 * Created by Mustafa Urgupluoglu on 5.02.2019.
 */

object TestUtils {

    @Throws(Exception::class)
    fun convertStreamToString(inputStream: InputStream): String {
        return inputStream.bufferedReader().use(BufferedReader::readText)
    }

    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String): String {
        val stream = context.resources.assets.open(filePath)

        val ret = convertStreamToString(stream)
        //Make sure you close all streams.
        stream.close()
        return ret
    }
}