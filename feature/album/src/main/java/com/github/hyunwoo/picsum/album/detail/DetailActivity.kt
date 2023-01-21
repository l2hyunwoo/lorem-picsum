package com.github.hyunwoo.picsum.album.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.hyunwoo.picsum.album.databinding.ActivityDetailBinding
import com.github.hyunwoo.picsum.album.model.PhotoUiModel
import com.github.hyunwoo.picsum.common.activity.BindingActivity
import com.github.hyunwoo.picsum.common.intent.parcelableExtra
import com.github.hyunwoo.picsum.image.load

class DetailActivity : BindingActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {
    private val photo by parcelableExtra<PhotoUiModel.PhotoParcel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.imageDetail.load(photo?.picture!!)
        binding.txtName.text = photo?.author
    }

    companion object {
        @JvmStatic
        fun getIntent(context: Context, photo: PhotoUiModel.PhotoParcel) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra("photo", photo)
            }
    }
}
