package com.github.hyunwoo.picsum.album.mapper

import com.github.hyunwoo.picsum.album.model.PhotoUiModel
import com.github.hyunwoo.picsum.domain.entity.Photo

fun Photo.toParcel() = PhotoUiModel.PhotoParcel(
    id = id,
    author = author,
    width = width,
    height = height,
    picture = downloadUrl
)

fun PhotoUiModel.PhotoParcel.asDomainModel() = Photo(
    id = id,
    author = author,
    width = width,
    height = height,
    url = "",
    downloadUrl = picture
)
