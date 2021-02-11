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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataModule {

    val module = module {

        single {
            val passphrase = SQLiteDatabase.getBytes("utsmannn".toCharArray())
            val factory = SupportFactory(passphrase)

            Room.databaseBuilder(
                androidContext(),
                FavoriteDataBase::class.java, "favorites.db"
            ).fallbackToDestructiveMigration()
                .openHelperFactory(factory)
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