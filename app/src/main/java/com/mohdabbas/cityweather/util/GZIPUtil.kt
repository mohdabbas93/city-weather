package com.mohdabbas.cityweather.util

import java.io.BufferedInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream
import javax.inject.Inject

/**
 * GZIP Util to deal with GZIP files
 *
 * @author Mohammad Abbas
 */
class GZIPUtil @Inject constructor() {

    /**
     * This function decompress the GZIP file and return the
     * input stream of its content
     *
     * @param inputStream The input stream of the Compressed file in GZIP format.
     * @return [GZIPInputStream] The input stream of the GZIP file.
     */
    fun getGZIPInputStream(inputStream: InputStream): GZIPInputStream {
        return GZIPInputStream(BufferedInputStream(inputStream))
    }
}