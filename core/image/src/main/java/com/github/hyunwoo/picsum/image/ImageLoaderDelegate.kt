package com.github.hyunwoo.picsum.image

import android.widget.ImageView
import com.github.hyunwoo.picsum.image.cache.disk.BitmapDiskCache
import com.github.hyunwoo.picsum.image.cache.memory.BitmapMemoryCache
import com.github.hyunwoo.picsum.image.config.ImageConfig
import com.github.hyunwoo.picsum.image.config.ServiceLocator

internal object ImageLoaderDelegate {
    private val diskCache = ServiceLocator.diskCache

    suspend fun loadFromRemote(
        view: ImageView,
        config: ImageConfig
    ) {
        BitmapMemoryCache.get(config.identifier)?.apply {
            view.setImageBitmap(this)
        } ?: diskCache.get(config)?.apply {
            view.setImageBitmap(this)
        } ?: run {
            val bitmap = ServiceLocator.remoteResourceFetcher.execute(config)
            view.setImageBitmap(bitmap)
            if (bitmap != null) {
                BitmapMemoryCache.put(config.identifier, bitmap)
                BitmapDiskCache.put(config, bitmap)
            }
        }
    }
}
