/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.di

import androidx.lifecycle.SavedStateHandle
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.utsman.wallpaper.viewmodel.DetailViewModel
import com.utsman.wallpaper.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainAppModule {

    val module = module {
        single { SplitInstallManagerFactory.create(get()) }
        single { SavedStateHandle() }
        viewModel { MainViewModel(get(), get()) }
        viewModel { DetailViewModel(get()) }
    }
}