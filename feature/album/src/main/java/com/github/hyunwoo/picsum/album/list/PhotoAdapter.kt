package com.github.hyunwoo.picsum.album.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.hyunwoo.picsum.album.databinding.ItemPhotoBinding
import com.github.hyunwoo.picsum.album.model.PhotoUiModel
import com.github.hyunwoo.picsum.common.view.recycerview.ItemDiffCallback

internal class PhotoAdapter(
    private val listener: ItemClickListener
) : PagingDataAdapter<PhotoUiModel, PhotoAdapter.PhotoViewHolder>(
    ItemDiffCallback(
        onContentsTheSame = { old, new -> old.id == new.id },
        onItemsTheSame = { old, new -> old == new }
    )
) {
    private lateinit var inflater: LayoutInflater

    fun interface ItemClickListener {
        fun onItemClick(photo: PhotoUiModel)
    }

    class PhotoViewHolder(
        private val binding: ItemPhotoBinding,
        private val listener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PhotoUiModel) {
            binding.txtItem.text = item.author
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }
}
