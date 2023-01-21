package com.github.hyunwoo.picsum.image

import android.content.Context
import com.github.hyunwoo.picsum.image.cache.disk.DiskCacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object ServiceLocator {
    @Volatile
    private lateinit var okHttpClient: OkHttpClient

    fun getOkHttpClient(): OkHttpClient {
        if (!::okHttpClient.isInitialized) {
            throw IllegalStateException("OkHttpClient must be initialized before use, call init first.")
        }
        return okHttpClient
    }

    fun init(context: Context) {
        if (!::okHttpClient.isInitialized) {
            synchronized(this) {
                if (!::okHttpClient.isInitialized) {
                    okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }
                        )
                        .addInterceptor(DiskCacheInterceptor())
                        .cache(Cache(context.cacheDir, 50 * 1024 * 1024))
                        .readTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .build()
                }
            }
        }
    }
}

