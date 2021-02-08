/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.utsman.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("select * from favorite order by millis desc")
    suspend fun getFavorite(): List<FavoriteEntity>

    @Insert
    suspend fun insertFavorite(entity: FavoriteEntity)

    @Query("delete from favorite where wallpaper_id = :id")
    suspend fun deleteFavorite(id: String)

    @Query("select * from favorite where wallpaper_id = :id")
    fun getById(id: String): Flow<FavoriteEntity?>
}