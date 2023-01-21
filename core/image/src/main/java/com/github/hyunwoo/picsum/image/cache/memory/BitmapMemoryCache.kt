package com.github.hyunwoo.picsum.image.cache.memory

import android.graphics.Bitmap
import androidx.collection.LruCache
import com.github.hyunwoo.picsum.common.log.debugLog


internal object BitmapMemoryCache {
    private const val KILOBYTE_UNIT = 1024
    private val maxMemory = (Runtime.getRuntime().maxMemory() / KILOBYTE_UNIT).toInt()
    private val maxCacheSize = maxMemory / 8
    private val cache by lazy {
        object : LruCache<Int, Bitmap>(maxCacheSize) {
            override fun sizeOf(key: Int, value: Bitmap): Int {
                return value.byteCount / KILOBYTE_UNIT
            }
        }
    }

    init {
        debugLog("RuntimeMemory Size $maxMemory")
        debugLog("BitmapMemoryCache initialized with max cache size: $maxCacheSize MB")
    }

    fun setCacheSize(cacheSize: Int, cacheSizeUnit: CacheSizeUnit) {
        val newCacheSize =  CacheSizeUnit.calculate(cacheSize, cacheSizeUnit)
        require(newCacheSize < maxMemory) {
            "Cache size is too large. Max cache size is $maxMemory MB"
        }
        cache.resize(newCacheSize)
    }

    fun get(key: Int): Bitmap? = cache[key]

    fun put(key: Int, bitmap: Bitmap) {
        cache.put(key, bitmap)
    }

    fun delete(key: Int): Bitmap? = cache.remove(key)

    fun clear() {
        cache.evictAll()
    }
}
