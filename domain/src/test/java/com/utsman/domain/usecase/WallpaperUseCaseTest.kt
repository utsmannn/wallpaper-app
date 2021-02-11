/*
 * Created by Muhammad Utsman on 12/2/21 12:06 AM
 * Copyright (c) 2021
 */

package com.utsman.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.utsman.data.remote.Services
import com.utsman.data.remote.interactor.ResultState
import com.utsman.data.remote.interactor.getDataWhenSuccess
import com.utsman.data.remote.interactor.getThrowableWhenError
import com.utsman.domain.*
import com.utsman.domain.entity.Wallpaper
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.io.IOException
import java.net.HttpURLConnection

class WallpaperUseCaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockWebServer = MockWebServer()
    private lateinit var services: Services
    private lateinit var wallpapersUseCase: WallpapersUseCase

    private val observerWallpaperPaged: Observer<PagingData<Wallpaper>> = mock()
    private val observerWallpaperDetail: Observer<ResultState<Wallpaper>> = mock()
    private val observerWallpaperHasFavorite: Observer<Boolean> = mock()

    private val wallpaperId1 = "bMmvCJJsAh8"
    private val wallpaperId2 = "4TBSG2Oqu0Q"

    @Before
    fun `before test`() {
        mockWebServer.start()
        services = mockNetworkServices(mockWebServer)

        wallpapersUseCase = FakeWallpaperInteractor(services)
        wallpapersUseCase.wallpaperDetail.observeForever(observerWallpaperDetail)
        wallpapersUseCase.wallpaperPaged.observeForever(observerWallpaperPaged)
        wallpapersUseCase.hasFavorite.observeForever(observerWallpaperHasFavorite)
    }

    @After
    @Throws(IOException::class)
    fun `after test`() {
        mockWebServer.shutdown()
    }

    @Test
    fun `wallpaper detail success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(readTestResourceFile("success_detail.json"))
        )
        wallpapersUseCase.getDetailWallpaper(wallpaperId1)
        val entityData = Wallpaper.createInstanceTest(id = wallpaperId1)
        val expectedSuccess = ResultState.Success(entityData)

        captor<ResultState<Wallpaper>>().run {
            verify(observerWallpaperDetail, times(2)).onChanged(capture())
            assertEquals(expectedSuccess.getDataWhenSuccess()?.id, value.getDataWhenSuccess()?.id)
        }
    }

    @Test
    fun `wallpaper detail error`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .setBody(readTestResourceFile("error.json"))
        )
        wallpapersUseCase.getDetailWallpaper(wallpaperId1)
        val expectedSuccess = ResultState.Error<Wallpaper>(Throwable("Error: Client Error"))

        captor<ResultState<Wallpaper>>().run {
            verify(observerWallpaperDetail, times(2)).onChanged(capture())
            assertEquals(expectedSuccess.getThrowableWhenError()?.message, value.getThrowableWhenError()?.message)
        }
    }

    @Test
    fun `wallpaper list success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(readTestResourceFile("success_list.json"))
        )

        wallpapersUseCase.getWallpaperPaged()
        val pagingData = PagingData.from(listOf(Wallpaper.createInstanceTest(), Wallpaper.createInstanceTest()))
        captor<PagingData<Wallpaper>>().run {
            verify(observerWallpaperPaged, times(1)).onChanged(capture())
            assertEquals(pagingData.getCount(), value.getCount())
        }
    }

    @Test
    fun `wallpaper list error`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .setBody(readTestResourceFile("error.json"))
        )

        wallpapersUseCase.getWallpaperPaged()
        captor<PagingData<Wallpaper>>().run {
            verify(observerWallpaperPaged, times(1)).onChanged(capture())
            assertEquals(0, value.getCount())
        }
    }

    @Test
    fun `wallpaper has favorite`() = runBlocking {
        wallpapersUseCase.addFavorite(Wallpaper.createInstanceTest(id = wallpaperId1))
        wallpapersUseCase.checkFavorite(wallpaperId1)

        captor<Boolean>().run {
            verify(observerWallpaperHasFavorite, times(1)).onChanged(capture())
            assertEquals(true, value)
        }
    }

    @Test
    fun `wallpaper has not favorite`() = runBlocking {
        wallpapersUseCase.addFavorite(Wallpaper.createInstanceTest(id = wallpaperId1))
        wallpapersUseCase.checkFavorite(wallpaperId2)

        captor<Boolean>().run {
            verify(observerWallpaperHasFavorite, times(1)).onChanged(capture())
            assertEquals(false, value)
        }
    }
}