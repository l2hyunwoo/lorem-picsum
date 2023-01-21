package com.github.hyunwoo.picsum.album.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.github.hyunwoo.picsum.album.mapper.asDomainModel
import com.github.hyunwoo.picsum.album.mapper.toParcel
import com.github.hyunwoo.picsum.album.model.PhotoUiModel
import com.github.hyunwoo.picsum.data.repository.GalleryPagingSource
import com.github.hyunwoo.picsum.domain.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val repository: GalleryRepository,
    private val pagingSource: GalleryPagingSource
) : ViewModel() {
    private val _likedPhotos = MutableStateFlow(emptySet<PhotoUiModel.PhotoParcel>())
    val notifyPhotoStateChangeFlow = MutableSharedFlow<Int>()

    init {
        viewModelScope.launch {
            _likedPhotos.update {
                repository.getLikedPhotos()
                    .map {
                        it.toParcel()
                            .also { photo -> photo.liked = _likedPhotos.value.contains(photo) }
                    }
                    .toSet()
            }
        }
    }

    fun likePhoto(photo: PhotoUiModel.PhotoParcel, position: Int) {
        viewModelScope.launch {
            repository.likePhoto(photo.asDomainModel())
            photo.liked = true
            _likedPhotos.update { it + photo }
            notifyPhotoStateChangeFlow.emit(position)
        }
    }

    fun dislikePhoto(photo: PhotoUiModel.PhotoParcel, position: Int) {
        viewModelScope.launch {
            repository.unlikePhoto(photo.asDomainModel())
            photo.liked = false
            _likedPhotos.update { it - photo }
            notifyPhotoStateChangeFlow.emit(position)
        }
    }

    val galleryPager = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { pagingSource }
    ).flow.map {
        it.map { photo ->
            photo.toParcel().also { photo -> photo.liked = _likedPhotos.value.contains(photo) }
        }
            .insertSeparators { before, after ->
                when {
                    before == null -> null
                    after == null -> null
                    else -> PhotoUiModel.Separator
                }
            }
    }.cachedIn(viewModelScope)
}
