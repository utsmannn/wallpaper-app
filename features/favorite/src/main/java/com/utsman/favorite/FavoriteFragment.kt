/*
 * Created by Muhammad Utsman on 8/2/21 12:12 PM
 * Copyright (c) 2021
 */

package com.utsman.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.utsman.favorite.databinding.FragmentFavoriteBinding
import com.utsman.wallpaper.adapter.PagingWallpaperAdapter
import com.utsman.wallpaper.detail.DetailFragment
import com.utsman.wallpaper.resString
import com.utsman.wallpaper.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val pagingAdapter = PagingWallpaperAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(FavoriteModule.module)
    }

    override fun onDetach() {
        super.onDetach()
        unloadKoinModules(FavoriteModule.module)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
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

            favoriteViewModel.favorites.observe(viewLifecycleOwner) { data ->
                containerEmpty.isVisible = data.isEmpty()
                lifecycleScope.launch {
                    pagingAdapter.submitData(PagingData.from(data))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.hideToolbarAndFab(false)
    }
}