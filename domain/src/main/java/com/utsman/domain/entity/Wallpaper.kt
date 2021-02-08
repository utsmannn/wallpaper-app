/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.domain.entity

data class Wallpaper(
    val id: String,
    val url: String,
    val urlMedium: String,
    val author: String,
    val downloadUrl: String,
    val blurHash: String,
    val color: String
)