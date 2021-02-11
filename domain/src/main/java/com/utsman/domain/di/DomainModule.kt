/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.domain.di

import com.utsman.domain.usecase.WallpaperInteractor
import com.utsman.domain.usecase.WallpapersUseCase
import org.koin.dsl.module

object DomainModule {

    val module = module {
        factory<WallpapersUseCase> {
            WallpaperInteractor(get(), get())
        }
    }
}