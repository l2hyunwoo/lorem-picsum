package com.github.hyunwoo.picsum.album.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.github.hyunwoo.picsum.album.mapper.toParcel
import com.github.hyunwoo.picsum.album.model.PhotoUiModel
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
    ).flow.map {
        it.map { photo -> photo.toParcel() }
            .insertSeparators { before, after ->
                when {
                    before == null -> null
                    after == null -> null
                    else -> PhotoUiModel.Separator
                }
            }
    }.cachedIn(viewModelScope)
}
