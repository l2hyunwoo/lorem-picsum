package com.github.hyunwoo.picsum

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.commit
import com.github.hyunwoo.picsum.album.list.PhotoListFragment
import com.github.hyunwoo.picsum.common.activity.BindingActivity
import com.github.hyunwoo.picsum.databinding.ActivityMainBinding
import com.github.hyunwoo.picsum.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bnvMain.setOnItemSelectedListener { option ->
            loadFragment(option.itemId)
            true
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.container_main, PhotoListFragment())
            }
            binding.bnvMain.selectedItemId = R.id.item_album
        }
    }

    private fun loadFragment(@IdRes itemId: Int) {
        supportFragmentManager.commit {
            replace(R.id.container_main, createFragmentOf(itemId))
        }
    }

    private fun createFragmentOf(@IdRes itemId: Int) = when (itemId) {
        R.id.item_album -> PhotoListFragment()
        R.id.item_profile -> ProfileFragment()
        else -> throw IllegalStateException("There's no adequate id from menu. Item Id: $itemId")
    }
}
