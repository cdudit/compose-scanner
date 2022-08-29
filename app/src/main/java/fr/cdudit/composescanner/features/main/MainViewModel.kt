package fr.cdudit.composescanner.features.main

import android.content.Context
import android.net.Uri
import android.os.FileObserver
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File

class MainViewModel : ViewModel() {
    var imageUri by mutableStateOf<Uri?>(null)
    var files by mutableStateOf<MutableList<File>>(mutableListOf())
    private var observer: FileObserver? = null

    private fun refreshFiles(context: Context) {
        files = mutableListOf()
        val storedFiles = File(context.cacheDir, "images")
        storedFiles.listFiles()?.forEach {
            if (it.name.startsWith("img_") && it.length() > 0) {
                files.add(it)
            }
        }
    }

    fun setupObserver(context: Context) {
        observer = object : FileObserver(File(context.cacheDir, "images")) {
            override fun onEvent(event: Int, file: String?) {
                if (file!= ".probe" && (event == CREATE || event == DELETE)) { // Created when camera is launched
                    refreshFiles(context)
                }
            }
        }
        observer?.startWatching()
        refreshFiles(context)
    }

    fun removeObserver() {
        observer?.stopWatching()
    }
}