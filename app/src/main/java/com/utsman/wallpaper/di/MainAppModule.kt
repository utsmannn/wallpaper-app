/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.di

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MainAppModule {

    @Provides
    @Singleton
    fun provideSplitInstallManager(@ApplicationContext context: Context) =
        SplitInstallManagerFactory.create(context)
}