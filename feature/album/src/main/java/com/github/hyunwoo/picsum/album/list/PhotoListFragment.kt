package com.github.hyunwoo.picsum.album.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.github.hyunwoo.picsum.album.databinding.FragmentPhotoListBinding
import com.github.hyunwoo.picsum.album.detail.DetailActivity
import com.github.hyunwoo.picsum.album.model.PhotoUiModel
import com.github.hyunwoo.picsum.common.fragment.BindingFragment
import com.github.hyunwoo.picsum.common.view.recycerview.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PhotoListFragment :
    BindingFragment<FragmentPhotoListBinding>(FragmentPhotoListBinding::inflate) {
    private val viewModel: PhotoListViewModel by activityViewModels()
    private var adapter: PhotoAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.notifyPhotoStateChangeFlow
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                adapter?.notifyItemChanged(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter = PhotoAdapter(object : PhotoAdapter.ItemClickListener {
            override fun onItemClick(photo: PhotoUiModel.PhotoParcel) {
                startActivity(DetailActivity.getIntent(requireContext(), photo))
            }

            override fun onLike(photo: PhotoUiModel.PhotoParcel, position: Int) {
                viewModel.likePhoto(photo, position)
            }

            override fun onDislike(photo: PhotoUiModel.PhotoParcel, position: Int) {
                viewModel.dislikePhoto(photo, position)
            }
        })

        binding.rvPhotos.adapter =
            adapter?.withLoadStateFooter(PagingLoadStateAdapter { adapter?.retry() })

        viewModel.galleryPager
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                adapter?.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter?.addLoadStateListener { it ->
            binding.rvPhotos.isVisible = it.refresh is LoadState.NotLoading
            binding.layoutLoadingList.root.isVisible = it.refresh is LoadState.Loading
            binding.layoutErrorList.root.isVisible = it.refresh is LoadState.Error
        }

        binding.layoutErrorList.btnRetry.setOnClickListener {
            adapter?.retry()
        }
    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()
    }
}