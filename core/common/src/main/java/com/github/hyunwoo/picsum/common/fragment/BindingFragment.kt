package com.github.hyunwoo.picsum.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BindingFragment<B : ViewBinding>(
    private val bindingInflater: Inflate<B>
) : Fragment() {
    private var _binding: B? = null
    protected val binding: B
        get() = requireNotNull(_binding) { "Binding not initialized." }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }
}
