package com.github.hyunwoo.picsum.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.hyunwoo.picsum.data.api.GalleryApi
import com.github.hyunwoo.picsum.data.constant.START_POSITION_INDEX
import com.github.hyunwoo.picsum.data.mapper.asDomainModel
import com.github.hyunwoo.picsum.domain.entity.Photo
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GalleryPagingSource @Inject constructor(
    private val network: GalleryApi
) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val currentPosition = params.key ?: START_POSITION_INDEX

        val response = runCatching { network.getPhotos(currentPosition) }
            .getOrElse { return LoadResult.Error(it) }
            .map { it.asDomainModel() }

        return LoadResult.Page(
            data = response,
            prevKey = if (currentPosition == START_POSITION_INDEX) null else currentPosition - 1,
            nextKey = if (response.isEmpty()) null else currentPosition + 1
        )
    }
}
