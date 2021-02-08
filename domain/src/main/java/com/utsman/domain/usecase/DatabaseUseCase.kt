/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.utsman.domain.entity.Wallpaper

interface DatabaseUseCase {
    val favorites: MutableLiveData<List<Wallpaper>>
    val hasFavorite: MutableLiveData<Boolean>
    suspend fun getFavorite()
    suspend fun addFavorite(wallpaper: Wallpaper)
    suspend fun removeFavorite(wallpaper: Wallpaper)
    suspend fun checkFavorite(wallpaperId: String)
}