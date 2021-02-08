/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.remote.interactor

import retrofit2.Response

sealed class ResultState<T: Any?> {
    class Loading<T: Any?> : ResultState<T>()
    class Idle<T: Any?>: ResultState<T>()
    data class Success<T: Any?>(val data: T) : ResultState<T>()
    data class Error<T: Any?>(val th: Throwable, val data: T? = null) : ResultState<T>()
}