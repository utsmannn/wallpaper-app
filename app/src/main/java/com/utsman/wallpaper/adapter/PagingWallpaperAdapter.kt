/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.utsman.domain.entity.Wallpaper
import com.utsman.wallpaper.R
import com.utsman.wallpaper.adapter.viewholder.WallpaperViewHolder

class PagingWallpaperAdapter : PagingDataAdapter<Wallpaper, WallpaperViewHolder>(HomeDiffUtil()) {

    class HomeDiffUtil : DiffUtil.ItemCallback<Wallpaper>() {
        override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private var _onClick: ((Wallpaper) -> Unit)? = null

    fun setOnClick(onClick: (Wallpaper) -> Unit) {
        _onClick = onClick
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item, _onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return WallpaperViewHolder(view)
    }
}