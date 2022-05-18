package com.mohdabbas.cityweather.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

/**
 * Has util functions to write and read the Json file that has the
 * city weather data.
 *
 * @author Mohammad Abbas
 * @constructor Create an instance of [InternalStorageReadWriteUtil]
 */
class InternalStorageReadWriteUtil @Inject constructor(@ApplicationContext private val context: Context) {

    /**
     * This function used to take an [inputStream] as input and write to
     * a file into the internal storage.
     *
     * @param inputStream the input stream to be written in the file.
     * @throws IOException if there are a problem in writing the file.
     * @return The file path of the saved file.
     */
    fun writeFileToInternalStorage(inputStream: InputStream): String {

        var fileOutputStream: FileOutputStream? = null
        var filePath = ""

        try {
            val buffer = ByteArray(1024)
            var length: Int

            val file = File(context.filesDir.path + File.separator + "WeatherData.json")

            filePath = file.path

            fileOutputStream = FileOutputStream(file)

            while (inputStream.read(buffer).also { length = it } != -1) {
                fileOutputStream.write(buffer, 0, length)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream.close()
            fileOutputStream?.close()
        }

        return filePath
    }

    /**
     * This function is used to get a file from the internal storage
     * providing the [filePath] and return that file.
     *
     * @param filePath The file path to be found.
     * @return The file that have the path [filePath]
     */
    fun readFileFromInternalStorage(filePath: String): File {
        return File(filePath)
    }
}