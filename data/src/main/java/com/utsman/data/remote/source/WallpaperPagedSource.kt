/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.data.remote.source

import androidx.paging.PagingSource
import com.utsman.data.remote.responses.ResponsesItem
import com.utsman.data.remote.Services
import java.net.SocketException
import java.net.SocketTimeoutException

class WallpaperPagedSource(private val services: Services) : PagingSource<Int, ResponsesItem>() {
    private var page = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponsesItem> {
        return try {
            val currentPage = params.key ?: page
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nexPage = currentPage + 1
            val response = services.getMainList(currentPage)
            page = currentPage
            val responseList = if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
            LoadResult.Page(responseList, prevPage, nexPage)
        } catch (e: Throwable) {
            LoadResult.Error(e)
        } catch (e: SocketTimeoutException) {
            LoadResult.Error(e)
        } catch (e: SocketException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}