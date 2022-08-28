package fr.cdudit.composescanner.features.main

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File

class MainViewModel : ViewModel() {
    var hasImage by mutableStateOf(false)
    var imageUri by mutableStateOf<Uri?>(null)
    var files by mutableStateOf<MutableList<File>>(mutableListOf())

    fun refreshFiles(context: Context) {
        files = mutableListOf()
        val storedFiles = File(context.cacheDir, "images")
        storedFiles.listFiles()?.forEach {
            files.add(it)
        }
    }
}