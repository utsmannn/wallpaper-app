/*
 * Created by Muhammad Utsman on 9/2/21 4:55 PM
 * Copyright (c) 2021
 */

package com.utsman.domain

import androidx.paging.*
import androidx.paging.filter
import com.utsman.core.Network
import com.utsman.data.local.Constants
import com.utsman.data.remote.Services
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume

fun mockNetworkServices(mockWebServer: MockWebServer): Services {
    return Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Services::class.java)
}

fun Any.readTestResourceFile(fileName: String): String {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
    return inputStream?.bufferedReader()?.readText() ?: ""
}

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
inline fun <reified T>captor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)

fun <T: Any>PagingData<T>.getCount(): Int {
    var size = 0
    this.map {
        size++
        true
    }
    return size
}