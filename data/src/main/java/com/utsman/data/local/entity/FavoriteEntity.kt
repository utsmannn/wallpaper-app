/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = Random.nextInt(),
    @ColumnInfo(name = "wallpaper_id")
    val _id: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "url_medium")
    val urlMedium: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "download_url")
    val downloadUrl: String,
    @ColumnInfo(name = "blur_hash")
    val blurHash: String,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "millis")
    val millis: Long
)