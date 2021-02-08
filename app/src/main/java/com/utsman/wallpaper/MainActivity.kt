/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.utsman.core.extensions.getStatusBarHeight
import com.utsman.core.extensions.makeStatusBarTransparent
import com.utsman.core.extensions.viewBinding
import com.utsman.wallpaper.databinding.ActivityMainBinding
import com.utsman.wallpaper.util.requestFeature
import com.utsman.wallpaper.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var splitInstallManager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            makeStatusBarTransparent(fakeStatusBar)
            transparentStatusBar.layoutParams.height = getStatusBarHeight()
            val navHostFragment =
                supportFragmentManager.findFragmentById(navHostContainer.id) as NavHostFragment
            val navController = navHostFragment.navController

            btnScrollUp.setOnClickListener {
                mainViewModel.actionScrollUp()
            }

            toolbarMain.run {
                inflateMenu(resMenu.main_menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.favorite -> {
                            val module = "favorite"
                            splitInstallManager.requestFeature(this@MainActivity, module) {
                                navController.navigate(resId.action_home_to_homeFavorite)
                            }
                        }
                    }
                    true
                }
            }

            mainViewModel.toolbarTitle.observe(this@MainActivity, {
                toolbarMain.title = it
            })

            val favoriteMenu = toolbarMain.menu.findItem(resId.favorite)
            mainViewModel.onVisibleHome.observe(this@MainActivity) { homeVisible ->
                binding.btnScrollUp.isVisible = homeVisible
                favoriteMenu.isVisible = homeVisible
                if (homeVisible) {
                    toolbarMain.navigationIcon = null
                } else {
                    toolbarMain.setNavigationIcon(resDrawable.ic_round_arrow_back_24)
                    toolbarMain.setNavigationOnClickListener {
                        onBackPressed()
                    }
                }
            }

            mainViewModel.hideToolbarAndFab.observe(this@MainActivity) { hide ->
                appbarMain.setExpanded(!hide)
            }
        }
    }
}