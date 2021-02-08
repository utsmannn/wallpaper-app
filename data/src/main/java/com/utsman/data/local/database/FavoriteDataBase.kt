/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.utsman.data.local.dao.FavoriteDao
import com.utsman.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}