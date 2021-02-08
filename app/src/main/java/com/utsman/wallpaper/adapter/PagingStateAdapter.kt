/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.utsman.wallpaper.R
import com.utsman.wallpaper.databinding.ItemLoadingBinding

class PagingStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<PagingStateAdapter.PagingStateViewHolder>() {

    class PagingStateViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: PagingStateViewHolder, loadState: LoadState) {
        val binding = ItemLoadingBinding.bind(holder.itemView)
        binding.run {
            progressCircular.isVisible = loadState is LoadState.Loading
            btnError.isVisible = loadState is LoadState.Error

            btnError.setOnClickListener {
                retry.invoke()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
        return PagingStateViewHolder(view)
    }
}