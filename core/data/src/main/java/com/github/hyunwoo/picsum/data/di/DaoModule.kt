package com.github.hyunwoo.picsum.data.di

import com.github.hyunwoo.picsum.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun providePhotoDao(
        appDatabase: AppDatabase
    ) = appDatabase.photoDao()
}
