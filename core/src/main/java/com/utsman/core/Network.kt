/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.core

import com.google.gson.Gson
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private fun okHttp(certificatePinner: CertificatePinner) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .certificatePinner(certificatePinner)
        .build()

    fun builder(url: String, gson: Gson, certificatePinner: CertificatePinner): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttp(certificatePinner))
        .build()
}