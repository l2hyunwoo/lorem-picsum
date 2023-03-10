package com.github.hyunwoo.picsum.image.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory

internal fun ByteArray.decodeSampledBitmap(
    url: String,
    requiredHeight: Int,
    requiredWidth: Int
): Bitmap {
    require(requiredWidth >= 0 && requiredHeight >= 0) { "Width and height must be greater than 0" }

    return BitmapFactory.Options().run {
        inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(this@decodeSampledBitmap, 0, size, this)
        inSampleSize = calculateInSampleSize(requiredWidth, requiredHeight)
        inJustDecodeBounds = false
        BitmapFactory.decodeByteArray(this@decodeSampledBitmap, 0, size, this)
    }
}

internal fun ByteArray.decodeSampledBitmap(
    requiredHeight: Int,
    requiredWidth: Int
): Bitmap {
    require(requiredWidth >= 0 && requiredHeight >= 0) { "Width and height must be greater than 0" }
    return BitmapFactory.Options().run {
        inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(this@decodeSampledBitmap, 0, size, this)
        inSampleSize = calculateInSampleSize(requiredWidth, requiredHeight)
        inJustDecodeBounds = false
        BitmapFactory.decodeByteArray(this@decodeSampledBitmap, 0, size, this)
    }
}

private fun BitmapFactory.Options.calculateInSampleSize(
    requestHeight: Int,
    requestWidth: Int
): Int {
    val (height, width) = this.run { outHeight to outWidth }
    if (width <= 0 || height <= 0) return 1
    var inSampleSize = 1L

    if (height > requestHeight || width > requestWidth) {
        val halfHeight = height / 2
        val halfWidth = width / 2

        while ((halfHeight / inSampleSize) >= requestHeight && (halfWidth / inSampleSize) >= requestWidth) {
            inSampleSize *= 2
        }
    }

    return inSampleSize.toInt()
}