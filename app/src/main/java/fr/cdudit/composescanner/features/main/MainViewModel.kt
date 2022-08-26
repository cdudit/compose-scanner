package fr.cdudit.composescanner.features.main

import androidx.lifecycle.ViewModel
import fr.cdudit.composescanner.models.File
import fr.cdudit.composescanner.models.sampleData
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel: ViewModel() {
    fun getFiles(): MutableList<File> {
        return sampleData
    }

    fun formatDate(date: Date): String {
        return SimpleDateFormat("dd MMMM Y", Locale.FRANCE).format(date.time)
    }
}