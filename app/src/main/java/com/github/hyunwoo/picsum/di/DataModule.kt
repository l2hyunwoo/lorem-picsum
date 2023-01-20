package com.github.hyunwoo.picsum.di

import com.github.hyunwoo.picsum.data.repository.GalleryRepositoryImpl
import com.github.hyunwoo.picsum.domain.repository.GalleryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindGalleryRepository(impl: GalleryRepositoryImpl): GalleryRepository
}
