package com.github.hyunwoo.picsum.common.log

import android.util.Log
import com.github.hyunwoo.picsum.common.BuildConfig

fun errorLog(message: String, throwable: Throwable) {
    if (BuildConfig.DEBUG) {
        Log.e("Picsum", message, throwable)
    }
}
