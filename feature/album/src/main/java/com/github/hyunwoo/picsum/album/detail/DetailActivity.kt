package com.github.hyunwoo.picsum.album.detail

import android.os.Bundle
import com.github.hyunwoo.picsum.album.databinding.ActivityDetailBinding
import com.github.hyunwoo.picsum.common.activity.BindingActivity

class DetailActivity : BindingActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
