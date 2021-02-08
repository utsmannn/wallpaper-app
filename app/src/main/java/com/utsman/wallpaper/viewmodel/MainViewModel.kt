/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.viewmodel

import android.os.Parcelable
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.utsman.domain.entity.Wallpaper
import com.utsman.domain.usecase.DatabaseUseCase
import com.utsman.domain.usecase.WallpapersUseCase
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val wallpapersUseCase: WallpapersUseCase,
    private val databaseUseCase: DatabaseUseCase,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    val wallpapers: LiveData<PagingData<Wallpaper>>
        get() = wallpapersUseCase.wallpaperPaged

    val favorites: LiveData<List<Wallpaper>>
        get() = databaseUseCase.favorites

    private val _actionScrollUp = MutableLiveData<Boolean>()
    private val _onVisibleHome = MutableLiveData<Boolean>()
    private val _toolbarTitle = MutableLiveData<String>()
    private val _hideToolbarAndFab = MutableLiveData<Boolean>()

    private var rvHomeState: Parcelable?
        get() = state.get("rv_home")
        set(value) {
            state.set("rv_home", value)
        }

    val actionScrollUp: LiveData<Boolean>
        get() = _actionScrollUp

    val onVisibleHome: LiveData<Boolean>
        get() = _onVisibleHome

    val toolbarTitle: LiveData<String>
        get() = _toolbarTitle

    val hideToolbarAndFab: LiveData<Boolean>
        get() = _hideToolbarAndFab

    init {
        viewModelScope.launch {
            wallpapersUseCase.getWallpaperPaged()
        }
    }

    fun actionScrollUp() = _actionScrollUp.postValue(true)
    fun onVisibleHome(visible: Boolean) = _onVisibleHome.postValue(visible)
    fun setToolbarTitle(title: String) = _toolbarTitle.postValue(title)

    fun resumeHomeRv(recyclerView: RecyclerView?) {
        _actionScrollUp.postValue(false)
        if (rvHomeState != null) {
            recyclerView?.layoutManager?.onRestoreInstanceState(rvHomeState)
        }
    }

    fun pausedHomeRv(recyclerView: RecyclerView?) {
        _actionScrollUp.postValue(false)
        rvHomeState = recyclerView?.layoutManager?.onSaveInstanceState()
    }

    fun hideToolbarAndFab(hide: Boolean) = _hideToolbarAndFab.postValue(hide)

    fun getFavorites() = viewModelScope.launch {
        databaseUseCase.getFavorite()
    }
}