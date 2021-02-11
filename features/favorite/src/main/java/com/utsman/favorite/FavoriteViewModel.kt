/*
 * Created by Muhammad Utsman on 8/2/21 12:00 PM
 * Copyright (c) 2021
 */

package com.utsman.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsman.domain.entity.Wallpaper
import com.utsman.favorite.usecase.DatabaseUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    val favorites: LiveData<List<Wallpaper>>
        get() = databaseUseCase.favorites

    init {
        viewModelScope.launch {
            databaseUseCase.getFavorite()
        }
    }
}