package com.github.hyunwoo.picsum.image.config

import android.graphics.Bitmap

enum class ImageMimeType {
    JPEG, PNG;

    companion object {
        fun toBitmapFormat(mimeType: ImageMimeType): Bitmap.CompressFormat {
            return when (mimeType) {
                JPEG -> Bitmap.CompressFormat.JPEG
                PNG -> Bitmap.CompressFormat.PNG
            }
        }
    }
}
