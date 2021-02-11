/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.remote.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Response
import kotlin.coroutines.resume

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

suspend fun <T : Any> ResultState<T>.getDataWhenSuccess(): T?  = suspendCancellableCoroutine { task ->
    if (this is ResultState.Success) {
        if (task.isActive) task.resume(data)
    } else {
        if (task.isActive) task.resume(null)
    }
}

suspend fun <T : Any> ResultState<T>.getThrowableWhenError(): Throwable?  = suspendCancellableCoroutine { task ->
    if (this is ResultState.Error) {
        if (task.isActive) task.resume(th)
    } else {
        if (task.isActive) task.resume(null)
    }
}