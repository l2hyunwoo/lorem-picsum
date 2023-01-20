package com.github.hyunwoo.picsum.profile

import android.os.Bundle
import android.view.View
import com.github.hyunwoo.picsum.common.fragment.BindingFragment
import com.github.hyunwoo.picsum.profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
