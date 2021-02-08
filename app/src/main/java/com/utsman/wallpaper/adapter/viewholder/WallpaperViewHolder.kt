/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.utsman.core.extensions.load
import com.utsman.core.helper.UnsplashBlurHashDecoder
import com.utsman.domain.entity.Wallpaper
import com.utsman.wallpaper.databinding.ItemHomeBinding

class WallpaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHomeBinding.bind(view)
    fun bind(wallpaper: Wallpaper, onClick: ((Wallpaper) -> Unit)?) = binding.run {
        imageItem.load(wallpaper.url, wallpaper.color, wallpaper.blurHash)
        root.setOnClickListener {
            onClick?.invoke(wallpaper)
        }
    }
}