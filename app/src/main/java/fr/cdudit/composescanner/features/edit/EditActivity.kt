package fr.cdudit.composescanner.features.edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.cdudit.composescanner.components.Thumbnail
import fr.cdudit.composescanner.components.Title
import fr.cdudit.composescanner.ui.theme.ComposeScannerTheme
import fr.cdudit.composescanner.utils.FileUtils.share
import java.io.File

@Composable
fun FileView(navController: NavController, fileName: String? = null) {
    ComposeScannerTheme {
        val context = LocalContext.current
        val file = File(context.cacheDir, "images/${fileName}")
        if (!file.exists()) {
            AlertDialog(
                onDismissRequest = { navController.popBackStack() },
                buttons = {
                    Button(onClick = { navController.popBackStack() }) {
                        Text("OK")
                    }
                },
                title = { Text("Une erreur est survenue") },
                text = { Text("Le fichier demandé n'existe pas") }
            )
        }
        EditFile(navController, file)
    }
}

@Composable
private fun EditFile(navController: NavController, file: File) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = { SaveFAB(file) }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 16.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .padding(0.dp)
                )
                Title(
                    "Edition",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Thumbnail(
                file = file,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SaveFAB(file: File) {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = { file.share(context) },
        contentColor = MaterialTheme.colors.onSecondary,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Icon(
            Icons.Default.Share,
            contentDescription = "Sauvegarde"
        )
    }
}