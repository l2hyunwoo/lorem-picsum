package com.github.hyunwoo.picsum.image

import android.content.Context
import android.widget.ImageView
import androidx.core.view.doOnAttach
import com.github.hyunwoo.picsum.image.config.ImageConfig
import com.github.hyunwoo.picsum.image.config.ImageMimeType
import com.github.hyunwoo.picsum.image.key.HashTransformer
import com.github.hyunwoo.picsum.image.key.HashType
import kotlinx.coroutines.*

object ImageLoader {
    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val hashTransformer: HashTransformer = ServiceLocator.hashTransformer
    private val remoteResourceFetcher = ServiceLocator.remoteResourceFetcher

    fun init(context: Context) {
        ServiceLocator.init(context)
    }

    fun load(url: String, view: ImageView) {
        require(url.isUrl) { "Url must contains schemes" }
        mainScope.launch {
            val imageConfig = ImageConfig(
                url = url,
                width = view.width,
                height = view.height,
                mimeType = ImageMimeType.JPEG,
                identifier = hashTransformer.execute(url, HashType.SHA256)
            )
            val bitmap = withContext(Dispatchers.IO) {
                remoteResourceFetcher.execute(imageConfig)
            }
            view.setImageBitmap(bitmap)
        }
    }

    private val String.isUrl: Boolean
        get() = startsWith("http") || startsWith("https")
}

fun ImageView.load(url: String) {
    doOnAttach {
        ImageLoader.load(url, this)
    }
}
