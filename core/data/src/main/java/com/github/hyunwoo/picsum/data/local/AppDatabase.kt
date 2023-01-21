package com.github.hyunwoo.picsum.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.hyunwoo.picsum.data.model.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}
