/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.domain.utils

import com.utsman.data.local.entity.FavoriteEntity
import com.utsman.data.remote.interactor.ResultState
import com.utsman.data.remote.responses.ResponsesDetail
import com.utsman.data.remote.responses.ResponsesItem
import com.utsman.domain.entity.Wallpaper

object DataMapper {

    fun mapResponseToWallpaper(itemResponse: ResponsesItem?): Wallpaper {
        return Wallpaper(
            id = itemResponse?.id ?: "",
            url = itemResponse?.urls?.thumb ?: "",
            urlMedium = itemResponse?.urls?.regular ?: "",
            author = itemResponse?.user?.name ?: "",
            downloadUrl = itemResponse?.urls?.full ?: "",
            blurHash = itemResponse?.blurHash ?: "",
            color = itemResponse?.color ?: "#fff"
        )
    }

    fun mapFavoriteToWallpaper(favoriteEntity: FavoriteEntity): Wallpaper {
        return Wallpaper(
            id = favoriteEntity._id,
            url = favoriteEntity.url,
            urlMedium = favoriteEntity.urlMedium,
            author = favoriteEntity.author,
            downloadUrl = favoriteEntity.downloadUrl,
            blurHash = favoriteEntity.blurHash,
            color = favoriteEntity.color
        )
    }

    fun mapWallpaperToEntity(wallpaper: Wallpaper): FavoriteEntity {
        return FavoriteEntity(
            _id = wallpaper.id,
            url = wallpaper.url,
            urlMedium = wallpaper.urlMedium,
            author = wallpaper.author,
            downloadUrl = wallpaper.downloadUrl,
            blurHash = wallpaper.blurHash,
            color = wallpaper.color,
            millis = System.currentTimeMillis()
        )
    }

    fun mapDetailToWallpaper(responsesDetail: ResponsesDetail?): Wallpaper {
        return Wallpaper(
            id = responsesDetail?.id ?: "",
            url = responsesDetail?.urls?.thumb ?: "",
            urlMedium = responsesDetail?.urls?.regular ?: "",
            author = responsesDetail?.user?.name ?: "",
            downloadUrl = responsesDetail?.urls?.full ?: "",
            blurHash = responsesDetail?.blurHash ?: "",
            color = responsesDetail?.color ?: "#fff"
        )
    }

    fun <T : Any, U : Any>mapResultState(resultState: ResultState<out T?>, map: (T?) -> U?): ResultState<U> {
        return when (resultState) {
            is ResultState.Success -> {
                val data = resultState.data
                val mapper = map.invoke(data)
                if (mapper == null) {
                    ResultState.Error(Throwable("Data null"))
                } else {
                    ResultState.Success(mapper)
                }
            }
            is ResultState.Idle -> ResultState.Idle()
            is ResultState.Error -> ResultState.Error(resultState.th)
            is ResultState.Loading -> ResultState.Loading()
        }
    }
}