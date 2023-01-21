package com.github.hyunwoo.picsum.data.mapper

import com.github.hyunwoo.picsum.data.model.NetworkPhotoResponse
import com.github.hyunwoo.picsum.data.model.PhotoEntity
import com.github.hyunwoo.picsum.domain.entity.Photo

fun NetworkPhotoResponse.asDomainModel() = Photo(
    id = id,
    author = author,
    width = width,
    height = height,
    url = url,
    downloadUrl = downloadUrl
)

fun PhotoEntity.asDomainModel() = Photo(
    id = id,
    author = author,
    width = width,
    height = height,
    url = url,
    downloadUrl = downloadUrl
)

fun Photo.toEntity() = PhotoEntity(
    id = id,
    author = author,
    width = width,
    height = height,
    url = url,
    downloadUrl = downloadUrl
)