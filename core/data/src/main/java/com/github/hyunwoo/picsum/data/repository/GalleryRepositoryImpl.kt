package com.github.hyunwoo.picsum.data.repository

import com.github.hyunwoo.picsum.data.local.PhotoDao
import com.github.hyunwoo.picsum.data.mapper.asDomainModel
import com.github.hyunwoo.picsum.data.mapper.toEntity
import com.github.hyunwoo.picsum.domain.entity.Photo
import com.github.hyunwoo.picsum.domain.repository.GalleryRepository
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val dao: PhotoDao
) : GalleryRepository {
    override suspend fun getLikedPhotos(): List<Photo> {
        return dao.getAll().map { it.asDomainModel() }
    }

    override suspend fun likePhoto(photo: Photo) {
        dao.insert(photo.toEntity())
    }

    override suspend fun unlikePhoto(photo: Photo) {
        dao.delete(photo.toEntity())
    }
}
