package com.github.hyunwoo.picsum.image.cache.disk

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.github.hyunwoo.picsum.common.log.errorLog
import com.github.hyunwoo.picsum.image.config.ImageConfig
import com.github.hyunwoo.picsum.image.config.ImageMimeType
import com.github.hyunwoo.picsum.image.utils.decodeSampledBitmap
import com.jakewharton.disklrucache.DiskLruCache
import okio.Buffer
import okio.sink
import java.io.File

internal object BitmapDiskCache {
    @Volatile
    private var diskLruCache: DiskLruCache? = null
    private var appVersion: Int = 1
    private var cacheSize = 500L

    private val Context.diskCacheDir: File
        get() = File(cacheDir.path + File.separator + "$packageName Bitmap Cache")

    fun init(context: Context) {
        if (diskLruCache == null) {
            synchronized(this) {
                if (diskLruCache == null) {
                    runCatching {
                        diskLruCache = DiskLruCache.open(
                            context.diskCacheDir,
                            appVersion,
                            1,
                            cacheSize * 1024 * 1024
                        )
                    }.onFailure {
                        errorLog("Picsum: Disk Cache is not initialized", it)
                    }
                }
            }
        }
    }

    fun setCacheSize(size: Long) {
        cacheSize = size
    }

    fun setAppVersion(version: Int) {
        appVersion = version
    }

    fun put(config: ImageConfig, bitmap: Bitmap) {
        val key = config.identifier
        val editor = diskLruCache?.edit(key)
        runCatching {
            val buffer = Buffer()
            if (diskLruCache?.get(key) == null) {
                if (
                    bitmap.compress(
                        ImageMimeType.toBitmapFormat(config.mimeType),
                        100,
                        buffer.outputStream()
                    )
                ) {
                    diskLruCache?.flush()
                    editor?.run {
                        newOutputStream(0)?.sink()?.write(buffer, buffer.size)
                        commit()
                    }
                    buffer.close()
                } else editor?.abort()
            }
        }.onFailure {
            editor?.abort()
            errorLog("Picsum: Disk Cache put failed", it)
        }
    }

    fun get(config: ImageConfig): Bitmap? {
        val key = config.identifier
        val snapshot = diskLruCache?.get(key)
        return snapshot?.getInputStream(0)?.use {
            it.readBytes().decodeSampledBitmap(config.height, config.width)
        }
    }
}
