/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.adapter.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.utsman.core.extensions.load
import com.utsman.domain.entity.Wallpaper
import com.utsman.wallpaper.R

class WallpaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(wallpaper: Wallpaper, onClick: ((Wallpaper) -> Unit)?) = itemView.run {
        val imageItem = findViewById<ImageView>(R.id.image_item)
        imageItem.load(wallpaper.url, wallpaper.color, wallpaper.blurHash)
        setOnClickListener {
            onClick?.invoke(wallpaper)
        }
    }
}