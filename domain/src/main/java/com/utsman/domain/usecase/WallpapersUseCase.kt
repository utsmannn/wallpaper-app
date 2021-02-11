/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.domain.usecase

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.utsman.data.remote.interactor.ResultState
import com.utsman.domain.entity.Wallpaper

interface WallpapersUseCase {
    val wallpaperPaged: MutableLiveData<PagingData<Wallpaper>>
    val wallpaperDetail: MutableLiveData<ResultState<Wallpaper>>
    suspend fun getWallpaperPaged()
    suspend fun getDetailWallpaper(id: String)

    val hasFavorite: MutableLiveData<Boolean>
    suspend fun addFavorite(wallpaper: Wallpaper)
    suspend fun removeFavorite(wallpaper: Wallpaper)
    suspend fun checkFavorite(wallpaperId: String)
}