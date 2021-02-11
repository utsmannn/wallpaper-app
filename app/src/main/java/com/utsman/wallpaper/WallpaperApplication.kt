/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.utsman.data.di.DataModule
import com.utsman.data.local.database.FavoriteDataBase
import com.utsman.domain.di.DomainModule
import com.utsman.wallpaper.di.MainAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WallpaperApplication : SplitCompatApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WallpaperApplication)
            modules(
                DataModule.module,
                DomainModule.module,
                MainAppModule.module
            )
        }
    }
}