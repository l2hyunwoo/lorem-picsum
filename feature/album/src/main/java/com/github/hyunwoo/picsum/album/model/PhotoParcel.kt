package com.github.hyunwoo.picsum.album.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PhotoUiModel {
    @Parcelize
    data class PhotoParcel(
        val id: Int,
        val author: String,
        val width: Int,
        val height: Int,
        val picture: String,
        var liked: Boolean = false
    ) : Parcelable, PhotoUiModel()

    object Separator : PhotoUiModel()
}
