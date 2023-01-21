package com.github.hyunwoo.picsum.image.cache.memory

enum class CacheSizeUnit(private val multiplier: Int) {
    BYTE(1),
    KILOBYTE(1024),
    MEGABYTE(1024 * 1024);

    companion object {
        fun calculate(size: Int, unit: CacheSizeUnit): Int {
            return size * unit.multiplier
        }
    }
}
