package com.github.hyunwoo.picsum.image.config

data class ImageConfig(
    val url: String,
    val width: Int,
    val height: Int,
    val mimeType: ImageMimeType,
    val identifier: String
)
