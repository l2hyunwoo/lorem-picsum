package com.github.hyunwoo.picsum.album.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.hyunwoo.picsum.album.databinding.ItemPhotoBinding
import com.github.hyunwoo.picsum.album.model.PhotoUiModel
import com.github.hyunwoo.picsum.common.view.recycerview.ItemDiffCallback

internal class PhotoAdapter : ListAdapter<PhotoUiModel, PhotoAdapter.PhotoViewHolder>(
    ItemDiffCallback(
        onContentsTheSame = { old, new -> old.id == new.id },
        onItemsTheSame = { old, new -> old == new }
    )
) {
    private lateinit var inflater: LayoutInflater

    class PhotoViewHolder(
        private val binding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PhotoUiModel) {
            binding.txtItem.text = item.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
