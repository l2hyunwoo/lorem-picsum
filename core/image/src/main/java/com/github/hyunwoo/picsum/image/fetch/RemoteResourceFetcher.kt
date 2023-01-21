package com.github.hyunwoo.picsum.image.fetch

import android.graphics.Bitmap
import com.github.hyunwoo.picsum.common.log.errorLog
import com.github.hyunwoo.picsum.image.ServiceLocator
import com.github.hyunwoo.picsum.image.config.ImageConfig
import com.github.hyunwoo.picsum.image.utils.decodeSampledBitmap
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.resumeWithException

class RemoteResourceFetcher(
    private val client: OkHttpClient = ServiceLocator.getOkHttpClient()
) {
    suspend fun execute(config: ImageConfig): Bitmap? = suspendCancellableCoroutine {
        val (url, width, height, mimeType, identifier) = config
        if (width <= 0 || height <= 0) {
            it.resume(null) { error ->
                errorLog("Invalid width or height", error)
            }
            return@suspendCancellableCoroutine
        }
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorLog("Failed to fetch image from remote", e)
                it.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                it.resume(
                    response.body?.byteStream()?.readBytes()
                        ?.decodeSampledBitmap(url, height, width)
                ) { error ->
                    errorLog("OnCancellation error in onResponse", error)
                    response.close()
                }
            }
        })
    }
}
