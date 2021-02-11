/*
 * Created by Muhammad Utsman on 8/2/21 1:43 PM
 * Copyright (c) 2021
 */

package com.utsman.favorite.usecase

import androidx.lifecycle.MutableLiveData
import com.utsman.data.local.database.FavoriteDataBase
import com.utsman.domain.entity.Wallpaper
import com.utsman.domain.utils.DataMapper
import kotlinx.coroutines.flow.collect

class DatabaseInteractor(
    private val favoriteDataBase: FavoriteDataBase,
) : DatabaseUseCase {

    override val favorites = MutableLiveData<List<Wallpaper>>()

    override suspend fun getFavorite() {
        val data = favoriteDataBase.favoriteDao()
            .getFavorite()
            .map { d ->
                DataMapper.mapFavoriteToWallpaper(d)
            }
        favorites.postValue(data)
    }
}