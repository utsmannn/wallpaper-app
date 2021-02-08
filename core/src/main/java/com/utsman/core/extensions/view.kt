/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.core.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.utsman.core.helper.UnsplashBlurHashDecoder

fun Context.getStatusBarHeight(): Int {
    var statusBarHeight = 0
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        statusBarHeight = resources.getDimensionPixelSize(resourceId)
    }
    return statusBarHeight
}

fun Activity.makeStatusBarTransparent(statusBar: View) {

    statusBar.layoutParams.height = getStatusBarHeight()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or 0
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun ImageView.load(url: String, color: String?, blurHash: String?) {
    val blur = if (blurHash != null) {
        blurBitmap(blurHash)
    } else {
        null
    }
    scaleType = ImageView.ScaleType.CENTER_CROP
    if (color != null) setBackgroundColor(Color.parseColor(color))
    Glide.with(context)
        .load(url)
        .apply {
            if (blur != null) thumbnail(Glide.with(context).load(blur))
        }
        .into(this)
        .clearOnDetach()
}

fun blurBitmap(blur: String?): Bitmap? {
    return UnsplashBlurHashDecoder.decode(
        blur,
        12,
        12
    )
}