/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.utsman.wallpaper.R
import com.utsman.wallpaper.adapter.PagingStateAdapter
import com.utsman.wallpaper.adapter.PagingWallpaperAdapter
import com.utsman.wallpaper.databinding.FragmentHomeBinding
import com.utsman.wallpaper.detail.DetailFragment
import com.utsman.wallpaper.resString
import com.utsman.wallpaper.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagingAdapter = PagingWallpaperAdapter()
        val pagingStateAdapter = PagingStateAdapter { pagingAdapter.retry() }

        with(binding) {
            mainViewModel.setToolbarTitle(getString(resString.app_name))
            recyclerViewHome.run {
                val gridLayout = GridLayoutManager(context, 2).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when (pagingStateAdapter.loadState) {
                                is LoadState.NotLoading -> 1
                                is LoadState.Loading -> if (position == pagingAdapter.itemCount) 2 else 1
                                is LoadState.Error -> if (position == pagingAdapter.itemCount) 2 else 1
                                else -> 1
                            }
                        }
                    }
                }
                layoutManager = gridLayout
                adapter = pagingAdapter.withLoadStateFooter(pagingStateAdapter)
            }

            pagingAdapter.setOnClick { wallpaper ->
                val bundle = bundleOf(
                    "id" to wallpaper.id,
                    "blur" to wallpaper.blurHash
                )
                findNavController().navigate(DetailFragment.HOME_TO_DETAIL, bundle)
                mainViewModel.hideToolbarAndFab(true)
            }

            pagingAdapter.addLoadStateListener {
                val state = it.refresh
                itemLoading.run {
                    progressCircular.isVisible = state is LoadState.Loading
                    btnError.isVisible = state is LoadState.Error
                    recyclerViewHome.isVisible = state is LoadState.NotLoading

                    btnError.setOnClickListener {
                        pagingAdapter.retry()
                    }
                }
            }

            mainViewModel.actionScrollUp.observe(viewLifecycleOwner) { scroll ->
                if (scroll) recyclerViewHome.smoothScrollToPosition(0)
            }

            mainViewModel.wallpapers.observe(viewLifecycleOwner) { data ->
                lifecycleScope.launch {
                    pagingAdapter.submitData(data)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.resumeHomeRv(binding.recyclerViewHome)
        mainViewModel.onVisibleHome(true)
        mainViewModel.hideToolbarAndFab(false)
    }

    override fun onPause() {
        super.onPause()
        mainViewModel.pausedHomeRv(binding.recyclerViewHome)
        mainViewModel.onVisibleHome(false)
    }
}