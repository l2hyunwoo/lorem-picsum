package com.github.hyunwoo.picsum.album.list

import android.os.Bundle
import android.view.View
import com.github.hyunwoo.picsum.album.databinding.FragmentPhotoListBinding
import com.github.hyunwoo.picsum.common.fragment.BindingFragment

class PhotoListFragment :
    BindingFragment<FragmentPhotoListBinding>(FragmentPhotoListBinding::inflate) {
    private val adapter: PhotoAdapter?
        get() = binding.rvPhotos.adapter as? PhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}