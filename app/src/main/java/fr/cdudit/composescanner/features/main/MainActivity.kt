package fr.cdudit.composescanner.features.main

import android.icu.text.CaseMap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.cdudit.composescanner.components.Files
import fr.cdudit.composescanner.components.Permission
import fr.cdudit.composescanner.components.Title
import fr.cdudit.composescanner.features.edit.FileView
import fr.cdudit.composescanner.ui.theme.ComposeScannerTheme
import fr.cdudit.composescanner.utils.FileUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeView(navController)
                }
                composable("file/{fileName}") {
                    FileView(navController, it.arguments?.getString("fileName"))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.setupObserver(applicationContext)
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeObserver()
    }

    @Composable
    private fun HomeView(navController: NavController) {
        ComposeScannerTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                floatingActionButton = { FloatingActionButton() },
                backgroundColor = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                ) {
                    Title(
                        title = "Fichiers",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Files(navController, viewModel.files)
                }
            }
        }
    }

    @Composable
    private fun FloatingActionButton() {
        val context = applicationContext
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = {
                if (!it && viewModel.imageUri != null && viewModel.imageUri?.lastPathSegment != null) {
                    File(cacheDir, "images/${viewModel.imageUri?.lastPathSegment}").delete()
                }
            }
        )
        Permission {
            FloatingActionButton(
                onClick = {
                    val uri = FileUtils.getImageUri(context)
                    viewModel.imageUri = uri
                    cameraLauncher.launch(uri)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Default.Add, "Ajouter")
            }
        }
    }
}
