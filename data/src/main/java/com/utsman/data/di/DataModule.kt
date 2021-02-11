/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.utsman.core.Network
import com.utsman.data.local.Constants
import com.utsman.data.local.database.FavoriteDataBase
import com.utsman.data.remote.Services
import okhttp3.CertificatePinner
import org.koin.dsl.module

object DataModule {

    val module = module {

        single {
            Room.databaseBuilder(get(), FavoriteDataBase::class.java, "favorite_db")
                .build()
        }

        single {
            val gson = GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create()

            val cert = CertificatePinner.Builder()
                .add(Constants.HOST_URL, Constants.PINS_CERT1)
                .add(Constants.HOST_URL, Constants.PINS_CERT2)
                .build()

            Network.builder(Constants.BASE_URL, gson, cert)
                .create(Services::class.java)
        }
    }
}