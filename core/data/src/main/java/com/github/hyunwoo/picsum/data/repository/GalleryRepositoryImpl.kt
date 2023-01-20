package com.github.hyunwoo.picsum.data.repository

import com.github.hyunwoo.picsum.data.api.GalleryApi
import com.github.hyunwoo.picsum.data.mapper.asDomainModel
import com.github.hyunwoo.picsum.domain.entity.Photo
import com.github.hyunwoo.picsum.domain.repository.GalleryRepository
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val network: GalleryApi
) : GalleryRepository {
    override suspend fun getPhotos(page: Int): List<Photo> {
        return network.getPhotos(page).map { it.asDomainModel() }
    }
}
