/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.utsman.data.local.database.FavoriteDataBase
import com.utsman.domain.entity.Wallpaper
import com.utsman.domain.utils.DataMapper
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DatabaseInteractor @Inject constructor(
    private val favoriteDataBase: FavoriteDataBase,
) : DatabaseUseCase {

    override val favorites = MutableLiveData<List<Wallpaper>>()
    override val hasFavorite = MutableLiveData<Boolean>()

    override suspend fun getFavorite() {
        val data = favoriteDataBase.favoriteDao()
            .getFavorite()
            .map { d ->
                DataMapper.mapFavoriteToWallpaper(d)
            }
        favorites.postValue(data)
    }

    override suspend fun addFavorite(wallpaper: Wallpaper) {
        val entity = DataMapper.mapWallpaperToEntity(wallpaper)
        favoriteDataBase.favoriteDao().insertFavorite(entity)
    }

    override suspend fun removeFavorite(wallpaper: Wallpaper) {
        favoriteDataBase.favoriteDao().deleteFavorite(wallpaper.id)
    }

    override suspend fun checkFavorite(wallpaperId: String) {
        favoriteDataBase.favoriteDao()
            .getById(wallpaperId)
            .collect { entity ->
                val added = entity != null
                hasFavorite.postValue(added)
            }
    }
}