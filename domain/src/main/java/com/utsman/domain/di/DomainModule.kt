/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.domain.di

import com.utsman.data.local.database.FavoriteDataBase
import com.utsman.data.remote.Services
import com.utsman.domain.usecase.DatabaseInteractor
import com.utsman.domain.usecase.DatabaseUseCase
import com.utsman.domain.usecase.WallpaperInteractor
import com.utsman.domain.usecase.WallpapersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    fun provideWallpaperUseCase(services: Services): WallpapersUseCase =
        WallpaperInteractor(services)

    @Provides
    @Singleton
    fun provideDatabaseUseCase(favoriteDataBase: FavoriteDataBase): DatabaseUseCase =
        DatabaseInteractor(favoriteDataBase)
}