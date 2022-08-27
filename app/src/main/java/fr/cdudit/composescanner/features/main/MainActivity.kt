package fr.cdudit.composescanner.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.cdudit.composescanner.R
import fr.cdudit.composescanner.components.Files
import fr.cdudit.composescanner.models.File
import fr.cdudit.composescanner.models.sampleData
import fr.cdudit.composescanner.ui.theme.ComposeScannerTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeScannerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = { FloatingActionButton() }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Column(
                            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        ) {
                            Title()
                            Files(viewModel.getFiles())
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Title() {
        Text(
            text = "Fichiers",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

    @Composable
    fun FloatingActionButton() {
        FloatingActionButton(
            onClick = {  },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(Icons.Default.Add, "")
        }
    }
}
