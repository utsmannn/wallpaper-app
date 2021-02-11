/*
 * Created by Muhammad Utsman on 8/2/21 4:34 PM
 * Copyright (c) 2021
 */

package com.utsman.favorite.usecase

import androidx.lifecycle.MutableLiveData
import com.utsman.domain.entity.Wallpaper

interface DatabaseUseCase {
    val favorites: MutableLiveData<List<Wallpaper>>
    suspend fun getFavorite()
}