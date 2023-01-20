package com.github.hyunwoo.picsum.album.mapper

import com.github.hyunwoo.picsum.album.model.PhotoUiModel
import com.github.hyunwoo.picsum.domain.entity.Photo

fun Photo.toUiModel() = PhotoUiModel(
    id = id,
    author = author,
    width = width,
    height = height,
    picture = downloadUrl
)