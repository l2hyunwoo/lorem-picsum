package com.github.hyunwoo.picsum.image

import android.content.Context
import android.widget.ImageView
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import com.github.hyunwoo.picsum.image.key.HashTransformer
import com.github.hyunwoo.picsum.image.key.HashTransformerImpl
import com.github.hyunwoo.picsum.image.load.RemoteResourceFetcher
import kotlinx.coroutines.*

object ImageLoader {
    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val hashTransformer: HashTransformer = HashTransformerImpl()
    private val remoteResourceFetcher by lazy {
        RemoteResourceFetcher()
    }

    fun init(context: Context) {
        ServiceLocator.init(context)
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
    doOnAttach {
        ImageLoader.load(url, this)
    }
}
