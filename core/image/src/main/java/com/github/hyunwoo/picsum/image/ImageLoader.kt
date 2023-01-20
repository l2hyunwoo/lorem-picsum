package com.github.hyunwoo.picsum.image

import android.widget.ImageView
import com.github.hyunwoo.picsum.image.key.HashTransformer
import com.github.hyunwoo.picsum.image.key.HashTransformerImpl
import kotlinx.coroutines.*

internal object ImageLoader {
    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val hashTransformer: HashTransformer = HashTransformerImpl()
    fun load(url: String, view: ImageView) {
        require(url.isUrl) { "Url must contains schemes" }
    }

    private val String.isUrl: Boolean
        get() = startsWith("http") || startsWith("https")
}

fun ImageView.load(url: String) {
    ImageLoader.load(url, this)
}
