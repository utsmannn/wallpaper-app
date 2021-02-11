/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.utsman.core.extensions.getStatusBarHeight
import com.utsman.core.extensions.makeStatusBarTransparent
import com.utsman.wallpaper.databinding.ActivityMainBinding
import com.utsman.wallpaper.util.requestFeature
import com.utsman.wallpaper.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val mainViewModel: MainViewModel by viewModel()

    private val splitInstallManager: SplitInstallManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
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