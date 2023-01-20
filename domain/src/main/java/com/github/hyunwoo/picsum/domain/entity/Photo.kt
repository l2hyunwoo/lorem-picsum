package com.github.hyunwoo.picsum.domain.entity

data class Photo(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String
)
