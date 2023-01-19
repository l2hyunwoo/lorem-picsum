package com.github.hyunwoo.picsum.album.list

import android.os.Bundle
import android.view.View
import com.github.hyunwoo.picsum.album.R
import com.github.hyunwoo.picsum.album.databinding.FragmentPhotoListBinding
import com.github.hyunwoo.picsum.common.fragment.BindingFragment

class PhotoListFragment : BindingFragment<FragmentPhotoListBinding>(R.layout.fragment_photo_list) {
    private val adapter: PhotoAdapter?
        get() = binding.rvPhotos.adapter as? PhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPhotos.adapter = PhotoAdapter()
    }
}