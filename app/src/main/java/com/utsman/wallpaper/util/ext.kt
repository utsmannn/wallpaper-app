/*
 * Created by Muhammad Utsman on 8/2/21 1:25 PM
 * Copyright (c) 2021
 */

package com.utsman.wallpaper.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.utsman.core.extensions.toast
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun SplitInstallManager.requestFeature(context: Context, feature: String, isInstalled: () -> Unit) {
    val installed = this.installedModules.contains(feature)
    if (installed) {
        isInstalled.invoke()
    } else {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Install downloader module")
            .setMessage("You can install downloader module")
            .setPositiveButton("Install") { v, _ ->
                val request = SplitInstallRequest.newBuilder()
                    .addModule(feature)
                    .build()

                this.startInstall(request)
                    .addOnSuccessListener {
                        context.toast("Success installing downloader module")
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                        context.toast("Failed installing downloader module")
                    }
                v.dismiss()
            }
            .setNegativeButton("Cancel") { v, _ ->
                v.dismiss()
            }
            .create()

        dialog.show()
    }
}