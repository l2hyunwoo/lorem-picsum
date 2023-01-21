package com.github.hyunwoo.picsum.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.hyunwoo.picsum.data.model.PhotoEntity

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    suspend fun getAll(): List<PhotoEntity>

    @Insert
    suspend fun insert(photo: PhotoEntity)

    @Delete
    suspend fun delete(photo: PhotoEntity)
}
