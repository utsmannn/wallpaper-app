/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsman.core.extensions.logd
import com.utsman.data.remote.interactor.ResultState
import com.utsman.domain.entity.Wallpaper
import com.utsman.domain.usecase.DatabaseUseCase
import com.utsman.domain.usecase.WallpapersUseCase
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val wallpapersUseCase: WallpapersUseCase,
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    val wallpaperDetail: LiveData<ResultState<Wallpaper>>
        get() = wallpapersUseCase.wallpaperDetail

    val hasFavorite: LiveData<Boolean>
        get() = databaseUseCase.hasFavorite

    fun getDetailWallpaper(id: String) = viewModelScope.launch {
        wallpapersUseCase.getDetailWallpaper(id)
    }

    fun checkFavorite(id: String) = viewModelScope.launch {
        databaseUseCase.checkFavorite(id)
    }

    fun toggleFavorite(wallpaper: Wallpaper) = viewModelScope.launch {
        logd("has favorite -> ${hasFavorite.value}")
        if (hasFavorite.value == true) {
            databaseUseCase.removeFavorite(wallpaper)
        } else {
            databaseUseCase.addFavorite(wallpaper)
        }
    }
}