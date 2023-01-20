package com.github.hyunwoo.picsum.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPhotoResponse(
    @SerialName("id")
    val id: String,
    @SerialName("author")
    val author: String,
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int,
    @SerialName("url")
    val url: String,
    @SerialName("download_url")
    val downloadUrl: String
)
