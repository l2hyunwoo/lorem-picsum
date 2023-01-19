package com.github.hyunwoo.picsum.album.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class PhotoUiModel(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val picture: String
) : Parcelable
