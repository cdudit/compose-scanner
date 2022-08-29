package fr.cdudit.composescanner.components

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import fr.cdudit.composescanner.utils.Constants
import fr.cdudit.composescanner.utils.DateUtils
import fr.cdudit.composescanner.utils.FileUtils
import fr.cdudit.composescanner.utils.FileUtils.isInPortrait
import fr.cdudit.composescanner.utils.FileUtils.share
import java.io.File
import java.util.*

@Composable
fun Files(files: MutableList<File>) {
    LazyColumn {
        items(files) {
            FileRow(it)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileRow(file: File) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    Permission(permission = Manifest.permission.READ_EXTERNAL_STORAGE) {
        if (openDialog.value) {
            Alert(
                file = file,
                onClick = {
                    openDialog.value = false
                    file.delete()
                },
                onDismiss = { openDialog.value = false }
            )
        }
        Card(
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .height(80.dp)
                .fillMaxWidth(),
            elevation = 0.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.combinedClickable(
                        onClick = { file.share(context) },
                        onLongClick = { openDialog.value = true }
                    )
                ) {
                Thumbnail(
                    file = file,
                    modifier = Modifier.rotate(if (file.isInPortrait()) 0f else 90f)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                ) {
                    FileContent(file)
                }
            }
        }
    }
}

@Composable
fun Alert(file: File, onClick: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Souhaitez-vous supprimer cet élément ?") },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Thumbnail(
                    file = file,
                    modifier = Modifier.height(80.dp)
                        .rotate(if (file.isInPortrait()) 0f else 90f)
                )
            }
       },
        buttons = {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = onClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Check, "OK")
                }
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Close, "Fermer")
                }
            }
        }
    )
}

@Composable
fun FileContent(file: File) {
    Text(
        modifier = Modifier.padding(bottom = 4.dp),
        text = file.name,
        fontSize = 18.sp
    )
    Text(
        text = DateUtils.formatDate(Date(file.lastModified()), "dd MMMM Y"),
        fontSize = 14.sp
    )
}

@Composable
fun Thumbnail(file: File, modifier: Modifier = Modifier) {
    if (file.length() > 0) {
        val bitmap = BitmapFactory.decodeFile(file.path).asImageBitmap()
        Image(
            modifier = modifier,
            bitmap = bitmap,
            contentDescription = "Thumbnail",
        )
    }
}