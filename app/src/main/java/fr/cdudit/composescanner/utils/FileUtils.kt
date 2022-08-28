package fr.cdudit.composescanner.utils

import android.content.Context
import android.content.Intent
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

    fun shareFile(context: Context, file: File) {
        val uri = FileProvider.getUriForFile(context, Constants.PROVIDER_AUTHORITY , file)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = Constants.MIME_TYPE_JPEG
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        val shareIntent = Intent.createChooser(sendIntent, file.name)
        context.startActivity(shareIntent)
    }
}