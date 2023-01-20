package com.github.hyunwoo.picsum.domain.repository

import com.github.hyunwoo.picsum.domain.entity.Photo

interface GalleryRepository {
    suspend fun getPhotos(page: Int): List<Photo>
}
