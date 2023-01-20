package com.github.hyunwoo.picsum.data.api

import com.github.hyunwoo.picsum.data.model.NetworkPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryApi {
    @GET("v2/list")
    suspend fun getPhotos(@Query("page") page: Int): List<NetworkPhotoResponse>
}
