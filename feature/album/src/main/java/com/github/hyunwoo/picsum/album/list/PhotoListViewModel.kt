package com.github.hyunwoo.picsum.album.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.github.hyunwoo.picsum.album.mapper.toUiModel
import com.github.hyunwoo.picsum.data.repository.GalleryPagingSource
import com.github.hyunwoo.picsum.domain.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val repository: GalleryRepository,
    private val pagingSource: GalleryPagingSource
) : ViewModel() {
    val galleryPager = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { pagingSource }
    ).flow.map { it.map { photo -> photo.toUiModel() } }.cachedIn(viewModelScope)
}
