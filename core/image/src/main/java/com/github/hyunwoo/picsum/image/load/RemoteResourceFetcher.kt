package com.github.hyunwoo.picsum.image.load

import android.graphics.Bitmap
import com.github.hyunwoo.picsum.common.log.errorLog
import com.github.hyunwoo.picsum.image.utils.decodeSampledBitmap
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RemoteResourceFetcher(
    private val client: OkHttpClient
) {
    fun execute(
        url: String,
        width: Int,
        height: Int
    ): Bitmap? {
        if (width <= 0 || height <= 0) {
            return null
        }
        val request = Request.Builder()
            .url(url)
            .build()
        var response: Response? = null
        try {
            response = client.newCall(request).execute()
            return response.body?.byteStream()?.readBytes()?.decodeSampledBitmap(url, height, width)
        } catch (e: IOException) {
            errorLog("Failed to fetch image from remote", e)
        } finally {
            response?.close()
        }

        return null
    }
}
