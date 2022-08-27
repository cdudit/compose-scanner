package fr.cdudit.composescanner.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.cdudit.composescanner.utils.DateUtils
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

@Composable
fun FileRow(file: File) {
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Thumbnail(file)
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            ) {
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
        }
    }
}

@Composable
fun Thumbnail(file: File, modifier: Modifier = Modifier) {
    val bitmap = BitmapFactory.decodeFile(file.path).asImageBitmap()
    Image(
        modifier = modifier,
        bitmap = bitmap,
        contentDescription = "Thumbnail"
    )
}