/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsman.core.extensions.logd
import com.utsman.data.remote.interactor.ResultState
import com.utsman.domain.entity.Wallpaper
import com.utsman.domain.usecase.WallpapersUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val wallpapersUseCase: WallpapersUseCase
) : ViewModel() {

    val wallpaperDetail: LiveData<ResultState<Wallpaper>>
        get() = wallpapersUseCase.wallpaperDetail

    val hasFavorite: LiveData<Boolean>
        get() = wallpapersUseCase.hasFavorite

    fun getDetailWallpaper(id: String) = viewModelScope.launch {
        wallpapersUseCase.getDetailWallpaper(id)
    }

    fun checkFavorite(id: String) = viewModelScope.launch {
        wallpapersUseCase.checkFavorite(id)
    }

    fun toggleFavorite(wallpaper: Wallpaper) = viewModelScope.launch {
        logd("has favorite -> ${hasFavorite.value}")
        if (hasFavorite.value == true) {
            wallpapersUseCase.removeFavorite(wallpaper)
        } else {
            wallpapersUseCase.addFavorite(wallpaper)
        }
    }
}