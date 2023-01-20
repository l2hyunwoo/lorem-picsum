package com.github.hyunwoo.picsum.album.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.github.hyunwoo.picsum.album.databinding.FragmentPhotoListBinding
import com.github.hyunwoo.picsum.album.detail.DetailActivity
import com.github.hyunwoo.picsum.common.fragment.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

        viewModel.galleryPager
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                adapter?.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)


    }
}