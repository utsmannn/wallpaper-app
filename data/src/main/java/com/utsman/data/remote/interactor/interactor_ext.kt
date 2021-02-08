/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.remote.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun <T : Any> fetchApi(
    call: suspend () -> Response<T>
): Flow<ResultState<out T?>> = flow {
    emit(ResultState.Loading<T>())
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            emit(ResultState.Success(data = response.body()))
        } else {
            emit(ResultState.Error<T>(th = Throwable("Error: ${response.message() ?: "Unknown"}")))
        }
    } catch (e: Throwable) {
        emit(ResultState.Error<T>(th = e))
    }
}