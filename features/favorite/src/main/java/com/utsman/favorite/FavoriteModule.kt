/*
 * Created by Muhammad Utsman on 8/2/21 1:42 PM
 * Copyright (c) 2021
 */

package com.utsman.favorite

import com.utsman.favorite.usecase.DatabaseInteractor
import com.utsman.favorite.usecase.DatabaseUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavoriteModule {

    val module = module {
        single<DatabaseUseCase> { DatabaseInteractor(get()) }
        viewModel { FavoriteViewModel(get()) }
    }
}