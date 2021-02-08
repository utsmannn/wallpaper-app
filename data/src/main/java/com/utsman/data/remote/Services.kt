/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.remote

import com.utsman.data.local.Constants
import com.utsman.data.remote.responses.ResponsesDetail
import com.utsman.data.remote.responses.ResponsesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {
    @GET(Constants.PHOTOS)
    suspend fun getMainList(
        @Query("page") page: Int,
        @Query("client_id") clientId: String = Constants.CLIENT_ID
    ): Response<List<ResponsesItem>>

    @GET(Constants.PHOTOS_DETAIL)
    suspend fun getDetail(
        @Path("id") id: String,
        @Query("client_id") clientId: String = Constants.CLIENT_ID
    ): Response<ResponsesDetail>
}