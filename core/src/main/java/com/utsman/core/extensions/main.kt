/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.core.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast

private const val TAG = "WALLPAPER_APP"

fun logd(msg: String?) = Log.d(TAG, msg ?: "empty message")
fun Context.toast(msg: String?, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, msg, duration).show()