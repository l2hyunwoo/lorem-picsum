package com.github.hyunwoo.picsum.album.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnDetach
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.hyunwoo.picsum.album.R
import com.github.hyunwoo.picsum.album.databinding.ItemPhotoBinding
import com.github.hyunwoo.picsum.album.databinding.ItemSeperatorBinding
import com.github.hyunwoo.picsum.album.model.PhotoUiModel
import com.github.hyunwoo.picsum.common.context.drawableOf
import com.github.hyunwoo.picsum.common.view.recycerview.ItemDiffCallback
import com.github.hyunwoo.picsum.image.load

internal class PhotoAdapter(
    private val listener: ItemClickListener
) : PagingDataAdapter<PhotoUiModel, RecyclerView.ViewHolder>(
    ItemDiffCallback(
        onContentsTheSame = { old, new ->
            if (old is PhotoUiModel.PhotoParcel && new is PhotoUiModel.PhotoParcel) {
                old.id == new.id
            } else false
        },
        onItemsTheSame = { old, new -> old == new }
    )
) {
    private lateinit var inflater: LayoutInflater

    interface ItemClickListener {
        fun onItemClick(photo: PhotoUiModel.PhotoParcel)

        fun onLike(photo: PhotoUiModel.PhotoParcel, position: Int)

        fun onDislike(photo: PhotoUiModel.PhotoParcel, position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is PhotoUiModel.Separator) {
            SEPERATOR
        } else {
            ITEM
        }
    }

    class PhotoViewHolder(
        private val binding: ItemPhotoBinding,
        private val listener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PhotoUiModel.PhotoParcel, position: Int) {
            val context = binding.root.context
            binding.txtItem.text = item.author
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
            binding.imgItem.load(item.picture)
            binding.imgLike.setOnClickListener {
                if (item.liked) {
                    listener.onDislike(item, position)
                } else {
                    listener.onLike(item, position)
                }
            }
            binding.imgLike.setImageDrawable(
                if (item.liked) {
                    context.drawableOf(R.drawable.ic_favorite)
                } else {
                    context.drawableOf(R.drawable.ic_favorite_border)
                }
            )
        }
    }

    class SeperatorViewHolder(
        private val binding: ItemSeperatorBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return when (viewType) {
            SEPERATOR -> {
                val binding = ItemSeperatorBinding.inflate(inflater, parent, false)
                SeperatorViewHolder(binding)
            }
            else -> {
                val binding = ItemPhotoBinding.inflate(inflater, parent, false)
                PhotoViewHolder(binding, listener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PhotoViewHolder -> {
                holder.onBind(getItem(position) as PhotoUiModel.PhotoParcel, position)
            }
            else -> {}
        }
    }

    companion object {
        private const val SEPERATOR = 0
        private const val ITEM = 1
    }
}
