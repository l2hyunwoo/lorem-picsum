package com.github.hyunwoo.picsum.album.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.hyunwoo.picsum.album.databinding.FragmentPhotoListBinding
import com.github.hyunwoo.picsum.album.detail.DetailActivity
import com.github.hyunwoo.picsum.album.util.ScrollPrefetchListener
import com.github.hyunwoo.picsum.common.fragment.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment :
    BindingFragment<FragmentPhotoListBinding>(FragmentPhotoListBinding::inflate) {
    private val viewModel: PhotoListViewModel by viewModels()
    private val adapter: PhotoAdapter?
        get() = binding.rvPhotos.adapter as? PhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPhotos.adapter = PhotoAdapter {
            startActivity(DetailActivity.getIntent(requireContext(), it))
        }
        binding.rvPhotos.addOnScrollListener(
            ScrollPrefetchListener {
                viewModel.loadNextPage()
            }
        )
    }
}