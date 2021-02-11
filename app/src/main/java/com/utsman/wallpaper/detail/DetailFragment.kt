/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.utsman.core.extensions.blurBitmap
import com.utsman.core.extensions.load
import com.utsman.data.remote.interactor.ResultState
import com.utsman.wallpaper.R
import com.utsman.wallpaper.databinding.FragmentDetailBinding
import com.utsman.wallpaper.resDrawable
import com.utsman.wallpaper.util.DownloaderUtils
import com.utsman.wallpaper.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object {
        const val HOME_TO_DETAIL = R.id.action_home_to_detailFragment
        const val FAVORITE_TO_DETAIL = R.id.action_favorite_to_detailFragment
    }

    private val binding: FragmentDetailBinding? by viewBinding()
    private val detailViewModel: DetailViewModel by viewModel()

    private val idWallpaper by lazy {
        arguments?.getString("id")
    }

    private val blurHash by lazy {
        arguments?.getString("blur")
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            bottomSheetBehavior = BottomSheetBehavior.from(containerBottomSheet)

            idWallpaper?.let { id ->
                detailViewModel.getDetailWallpaper(id)
                detailViewModel.checkFavorite(id)
            }

            val blurBitmap = blurBitmap(blurHash)
            imageDetail.run {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageBitmap(blurBitmap)
            }

            detailViewModel.wallpaperDetail.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ResultState.Idle -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    }
                    is ResultState.Loading -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    }
                    is ResultState.Success -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                        val data = state.data
                        imageDetail.load(data.urlMedium, data.color, null)
                        textViewAlt.setAlt(data.author)

                        imageViewFavorite.setOnClickListener {
                            detailViewModel.toggleFavorite(data)
                        }

                        imageViewDownload.setOnClickListener {
                            DownloaderUtils.startDownload(data.downloadUrl, requireContext(), idWallpaper)
                        }
                    }
                    is ResultState.Error -> {
                        val throwable = state.th
                        throwable.printStackTrace()
                    }
                }
            }

            detailViewModel.hasFavorite.observe(viewLifecycleOwner) { hasFavorite ->
                val favoriteIconRes = if (hasFavorite) {
                    resDrawable.ic_round_favorite_24
                } else {
                    resDrawable.ic_round_favorite_border_24
                }

                imageViewFavorite.setImageResource(favoriteIconRes)
            }
        }
    }

    private fun TextView.setAlt(author: String) {
        val template = "Photo by $author <a href=\"http://www.unsplash.com\">on Unsplash</a>"
        val html = HtmlCompat.fromHtml(template, HtmlCompat.FROM_HTML_MODE_COMPACT)
        text = html
    }

}