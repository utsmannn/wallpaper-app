/*
 * Created by Muhammad Utsman on 6/2/21 10:01 PM
 * Copyright (c) 2021
 */

package com.utsman.downloader

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.utsman.core.extensions.logd
import com.utsman.core.extensions.toast

class DownloaderUtils {

    init {
        logd("downloader utils created...")
    }

    fun startDownload(url: String, context: Context, id: String) {
        logd("function download started...")
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        context.toast("Download $id.jpg started")
        val downloadRequest = DownloadManager.Request(Uri.parse(url)).apply {
            val notifyComplete = DownloadManager.Request.VISIBILITY_VISIBLE or DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            setNotificationVisibility(notifyComplete)
            setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "$id.jpg")
            setAllowedOverMetered(true)
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_MOBILE)
            setTitle("Downloading ${id}.jpg")
        }

        downloadManager.enqueue(downloadRequest)
    }
}