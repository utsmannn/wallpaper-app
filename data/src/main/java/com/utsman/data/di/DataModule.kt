/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.utsman.core.Network
import com.utsman.data.local.Constants
import com.utsman.data.local.dao.FavoriteDao
import com.utsman.data.local.database.FavoriteDataBase
import com.utsman.data.remote.Services
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .setPrettyPrinting()
        .create()

    @Provides
    @Singleton
    fun provideService(gson: Gson): Services = Network.builder(Constants.BASE_URL, gson)
        .create(Services::class.java)

    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteDataBase =
        Room.databaseBuilder(context, FavoriteDataBase::class.java, "favorite_db")
            .build()
}