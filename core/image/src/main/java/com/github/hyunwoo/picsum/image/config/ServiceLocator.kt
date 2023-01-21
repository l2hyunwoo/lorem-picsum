package com.github.hyunwoo.picsum.image.config

import android.content.Context
import com.github.hyunwoo.picsum.image.cache.disk.BitmapDiskCache
import com.github.hyunwoo.picsum.image.fetch.RemoteResourceFetcher
import com.github.hyunwoo.picsum.image.key.HashTransformer
import com.github.hyunwoo.picsum.image.key.HashTransformerImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

internal object ServiceLocator {
    @Volatile
    private lateinit var okHttpClient: OkHttpClient
    val diskCache = BitmapDiskCache
    val hashTransformer: HashTransformer by lazy { HashTransformerImpl() }
    val remoteResourceFetcher: RemoteResourceFetcher by lazy { RemoteResourceFetcher() }

    fun getOkHttpClient(): OkHttpClient {
        if (!ServiceLocator::okHttpClient.isInitialized) {
            throw IllegalStateException("OkHttpClient must be initialized before use, call init first.")
        }
        return okHttpClient
    }

    fun init(context: Context) {
        if (!ServiceLocator::okHttpClient.isInitialized) {
            synchronized(this) {
                if (!ServiceLocator::okHttpClient.isInitialized) {
                    okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }
                        )
                        .readTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .build()
                }
            }
        }
        diskCache.init(context)
    }
}

