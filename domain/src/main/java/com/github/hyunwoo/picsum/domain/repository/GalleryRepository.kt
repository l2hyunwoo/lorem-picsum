package com.github.hyunwoo.picsum.domain.repository

import com.github.hyunwoo.picsum.domain.entity.Photo

interface GalleryRepository {
    suspend fun getLikedPhotos(): List<Photo>

    suspend fun likePhoto(photo: Photo)

    suspend fun unlikePhoto(photo: Photo)
}
