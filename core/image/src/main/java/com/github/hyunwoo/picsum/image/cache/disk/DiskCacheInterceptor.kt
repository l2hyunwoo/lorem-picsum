package com.github.hyunwoo.picsum.image.cache.disk

import com.github.hyunwoo.picsum.common.log.debugLog
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class DiskCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl = CacheControl.Builder()
            .maxStale(15, TimeUnit.DAYS)
            .build()

        val request = chain.request()
            .newBuilder()
            .removeHeader(PRAGMA)
            .removeHeader(CACHE_CONTROL)
            .cacheControl(cacheControl)
            .build()

        debugLog("DiskCacheInterceptor: $request")
        val response: Response = chain.proceed(request)
        debugLog("DiskCacheInterceptor: $response ${response.cacheResponse} ${response.networkResponse} ${response.cacheControl}")
        return response
    }

    companion object {
        private const val CACHE_CONTROL = "Cache-Control"
        private const val PRAGMA = "Pragma"
    }
}
