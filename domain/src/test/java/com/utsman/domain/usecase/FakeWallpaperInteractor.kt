/*
 * Created by Muhammad Utsman on 12/2/21 12:17 AM
 * Copyright (c) 2021
 */

package com.utsman.domain.usecase

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.utsman.data.local.database.FavoriteDataBase
import com.utsman.data.local.entity.FavoriteEntity
import com.utsman.data.remote.Services
import com.utsman.data.remote.interactor.ResultState
import com.utsman.data.remote.interactor.fetchApi
import com.utsman.domain.entity.Wallpaper
import com.utsman.domain.usecase.WallpapersUseCase
import com.utsman.domain.utils.DataMapper
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class FakeWallpaperInteractor(
    private val services: Services
) : WallpapersUseCase {
    override val wallpaperPaged =  MutableLiveData<PagingData<Wallpaper>>()
    override val wallpaperDetail = MutableLiveData<ResultState<Wallpaper>>()

    private val fakeListFavorite = mutableListOf<FavoriteEntity>()

    override suspend fun getWallpaperPaged() {
        val fakePaged = PagingData.from(
            listOf(Wallpaper.createInstanceTest(), Wallpaper.createInstanceTest())
        )

        wallpaperPaged.postValue(fakePaged)
    }

    override suspend fun getDetailWallpaper(id: String) {
        fetchApi {
            services.getDetail(id)
        }.map {
            DataMapper.mapResultState(it) { response ->
                DataMapper.mapDetailToWallpaper(response)
            }
        }.collect {
            wallpaperDetail.postValue(it)
        }
    }

    override val hasFavorite = MutableLiveData<Boolean>()

    override suspend fun addFavorite(wallpaper: Wallpaper) {
        val entity = DataMapper.mapWallpaperToEntity(wallpaper)
        fakeListFavorite.add(entity)
    }

    override suspend fun removeFavorite(wallpaper: Wallpaper) {
        val entity = DataMapper.mapWallpaperToEntity(wallpaper)
        fakeListFavorite.remove(entity)
    }

    override suspend fun checkFavorite(wallpaperId: String) {
        fakeListFavorite.asFlow()
            .collect { entity ->
                val added = entity._id == wallpaperId
                hasFavorite.postValue(added)
            }
    }
}