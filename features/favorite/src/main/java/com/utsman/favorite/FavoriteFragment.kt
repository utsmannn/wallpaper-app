/*
 * Created by Muhammad Utsman on 8/2/21 12:12 PM
 * Copyright (c) 2021
 */

package com.utsman.favorite

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.utsman.core.extensions.logd
import com.utsman.core.extensions.viewBinding
import com.utsman.data.local.database.FavoriteDataBase
import com.utsman.domain.usecase.DatabaseInteractor
import com.utsman.favorite.databinding.FragmentFavoriteBinding
import com.utsman.wallpaper.adapter.PagingWallpaperAdapter
import com.utsman.wallpaper.detail.DetailFragment
import com.utsman.wallpaper.resString
import com.utsman.wallpaper.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding? by viewBinding()

    private val mainViewModel: MainViewModel by activityViewModels()
    private val pagingAdapter = PagingWallpaperAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            mainViewModel.setToolbarTitle(getString(resString.favorite))
            recyclerViewFavorite.run {
                val gridLayout = GridLayoutManager(context, 2)
                layoutManager = gridLayout
                adapter = pagingAdapter
            }

            mainViewModel.actionScrollUp.observe(viewLifecycleOwner) { scroll ->
                if (scroll) recyclerViewFavorite.smoothScrollToPosition(0)
            }

            pagingAdapter.setOnClick { wallpaper ->
                val bundle = bundleOf(
                    "id" to wallpaper.id,
                    "blur" to wallpaper.blurHash
                )
                findNavController().navigate(DetailFragment.FAVORITE_TO_DETAIL, bundle)
                mainViewModel.hideToolbarAndFab(true)
            }

            mainViewModel.getFavorites()
            mainViewModel.favorites.observe(viewLifecycleOwner) { data ->
                containerEmpty.isVisible = data.isEmpty()
                lifecycleScope.launch {
                    pagingAdapter.submitData(PagingData.from(data))
                }
            }
        }
    }

    private fun inject() {

    }

    override fun onResume() {
        super.onResume()
        mainViewModel.hideToolbarAndFab(false)
    }
}