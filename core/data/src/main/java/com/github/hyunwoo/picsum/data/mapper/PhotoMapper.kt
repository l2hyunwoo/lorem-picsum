package com.github.hyunwoo.picsum.data.mapper

import com.github.hyunwoo.picsum.data.model.NetworkPhotoResponse
import com.github.hyunwoo.picsum.domain.entity.Photo

fun NetworkPhotoResponse.asDomainModel() = Photo(
    id = id,
    author = author,
    width = width,
    height = height,
    url = url,
    downloadUrl = downloadUrl
)