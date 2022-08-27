package fr.cdudit.composescanner.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import java.io.File

object FileUtils {
    fun getImageUri(context: Context): Uri {
        val directory = File(context.cacheDir, "images")
        directory.mkdirs()
        val file = File.createTempFile(
            "img_",
            ".jpg",
            directory
        )
        val authority = context.packageName + ".fileprovider"
        return FileProvider.getUriForFile(
            context,
            authority,
            file,
        )
    }
}