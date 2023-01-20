package com.github.hyunwoo.picsum.image

import android.widget.ImageView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.github.hyunwoo.picsum.image.key.HashTransformer
import com.github.hyunwoo.picsum.image.key.HashTransformerImpl
import com.github.hyunwoo.picsum.image.load.RemoteResourceFetcher
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal object ImageLoader {
    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val hashTransformer: HashTransformer = HashTransformerImpl()
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }
    private val remoteResourceFetcher by lazy {
        RemoteResourceFetcher(okHttpClient)
    }

    fun load(url: String, view: ImageView) {
        require(url.isUrl) { "Url must contains schemes" }
        mainScope.launch {
            val bitmap = withContext(Dispatchers.IO) {
                remoteResourceFetcher.execute(url, view.width, view.height)
            }
            view.setImageBitmap(bitmap)
        }
    }

    private val String.isUrl: Boolean
        get() = startsWith("http") || startsWith("https")
}

fun ImageView.load(url: String) {
    ImageLoader.load(url, this)
}
