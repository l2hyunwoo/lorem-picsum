package com.github.hyunwoo.picsum.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<B : ViewBinding>(
    @LayoutRes private val resId: Int
) : Fragment() {
    private var _binding: B? = null
    protected val binding: B
        get() = requireNotNull(_binding) { "Binding not initialized." }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, resId, container, false)
        return binding.root
    }
}
