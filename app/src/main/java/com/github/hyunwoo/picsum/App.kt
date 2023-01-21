package com.github.hyunwoo.picsum

import android.app.Application
import com.github.hyunwoo.picsum.image.ImageLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ImageLoader.init(this)
    }
}
