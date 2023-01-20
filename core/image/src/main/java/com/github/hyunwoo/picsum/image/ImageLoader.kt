package com.github.hyunwoo.picsum.image

import android.widget.ImageView

internal object ImageLoader {
    fun load(url: String, view: ImageView) {
        require(url.isUrl) { "Url must contains schemes" }
    }

    private val String.isUrl: Boolean
        get() = startsWith("http") || startsWith("https")
}

fun ImageView.load(url: String) {
    ImageLoader.load(url, this)
}
