package com.eru.pixasearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eru.pixasearch.databinding.ItemImageBinding

class ImageAdapter(private val list: List<ImageModel>):RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(imageModel: ImageModel) {
            binding.imageView.load(imageModel.largeImageURL)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount() = list.size
}